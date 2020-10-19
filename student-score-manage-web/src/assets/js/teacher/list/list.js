(function () {
    let server_url = '';
    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#admission-date').datepicker({ language: 'zh-CN' });
        $('#resign-date').datepicker({ language: 'zh-CN' });
        build_teacher_table(server_url, 1);
        $('#register-teacher').on('click', function () { to_register_taecher(); });
        $('#cancel-teacher').on('click', function () { clear_teacher(); });
        $('#save-teacher').on('click', function () { save_teacher(server_url) });
    });
}());

function build_teacher_table(server_url, current_page) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/teacher/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let teacher_no_td = $('<td></td>');
                    teacher_no_td.text(obj['no']);
                    let teacher_name_td = $('<td></td>');
                    teacher_name_td.text(obj['name']);
                    let teacher_gender_td = $('<td></td>');
                    teacher_gender_td.text(obj['gender'] === 1 ? '男' : '女');
                    let teacher_telephone_td = $('<td></td>');
                    teacher_telephone_td.text(obj['telephone']);
                    let teacher_admission_date_td = $('<td></td>');
                    teacher_admission_date_td.text(obj['admissionDate']);
                    let teacher_status_td = $('<td></td>');
                    teacher_status_td.text(obj['status'] === 0 ? '在职' : '离职');
                    let option_td = $('<td></td>');
                    let edit_teacher_btn = $('<button>编辑</button>');
                    edit_teacher_btn.attr({ 'class': 'btn btn-outline-primary btn-sm' })
                    edit_teacher_btn.on('click', function () { to_edit_teacher(server_url, obj['no']) });
                    option_td.append(edit_teacher_btn);

                    let tr = $('<tr></tr>');
                    tr.append(teacher_no_td);
                    tr.append(teacher_name_td);
                    tr.append(teacher_gender_td);
                    tr.append(teacher_telephone_td);
                    tr.append(teacher_admission_date_td);
                    tr.append(teacher_status_td);
                    tr.append(option_td);
                    $('#table-content').append(tr);
                });
                build_teacher_table_pagination(current_page, result['totalPage'], server_url);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function build_teacher_table_pagination(current_page, total_page, server_url) {
    $('#teacher-pagination').html('');
    let page_interval = 2;
    let start_index = current_page - page_interval;
    if (start_index < 1) {
        start_index = 1;
        page_interval = page_interval + Math.abs(current_page - 1 - page_interval);
    }

    let end_index = current_page + page_interval > total_page ? total_page : current_page + page_interval;



    for (let i = start_index; i <= end_index; i++) {
        let page_link = $('<a class=\'page-link\'></a>');
        page_link.text(i);
        page_link.on('click', function () { build_teacher_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#teacher-pagination').append(page_li);
    }



    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_teacher_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#teacher-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_teacher_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#teacher-pagination').append(next_li);
}

function save_teacher(server_url) {
    console.log('save_teacher');
    let input_arr = $('#teacher-data input');
    let data = {}
    $.each(input_arr, function (index, obj) {
        let required_attr = $(obj).attr('required');
        let input_name = $(obj).attr('name');
        let inputVal = $(obj).val();
        if (required_attr === 'required') {
            if (inputVal === '') {
                console.log('val null');
            }
        }
        data[input_name] = inputVal;
    });
    let gender = $('#gender').val();
    let status = $('#status').val();
    data['gender'] = gender;
    data['status'] = status;
    console.log(data);
    let api_url = server_url + '/teacher/register';
    if ($('#action').val() === 'update') {
        api_url = server_url + '/teacher/update';
    }


    $.ajax({
        url: api_url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.bootstrapGrowl('保存成功', { type: 'success' });
                $('#edit-teacher-modal').modal('hide');
                build_teacher_table(server_url, 1);

            }
        },
        error: function () {
            $.bootstrapGrowl('保存失败', { type: 'danger' });
        }
    });
}

function to_edit_teacher(server_url, teacher_no) {
    clear_teacher();
    $.ajax({
        url: server_url + '/teacher/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'no': teacher_no, 'pageSize': 1, 'currentPage': 1 },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === "00") {
                let teacher = data[0];
                $('#id').val(teacher['id']);
                $('#no').val(teacher['no']);
                $('#name').val(teacher['name']);
                $('#admission-date').val(teacher['admissionDate']);
                $('#resign-date').val(teacher['resignDate']);
                $('#telephone').val(teacher['telephone']);
                $('#gender').val(teacher['gender']);
                $('#status').val(teacher['status']);
                $('#action').val('update');
                $('#edit-teacher-modal').modal({ 'show': true });
            }
        }
    });
}

function clear_teacher() {
    $('#id').val('');
    $('#no').val('');
    $('#name').val('');
    $('#admission-date').val('');
    $('#resign-date').val('');
    $('#telephone').val('');
    $('#action').val('register');
}

function to_register_taecher() {
    clear_teacher();
    $('#edit-teacher-modal').modal({ 'show': true });
}