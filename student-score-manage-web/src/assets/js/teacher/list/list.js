(function () {
    let server_url = '';
    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#admission-date').datepicker({ language: 'zh-CN' });
        $('#resign-date').datepicker({ language: 'zh-CN' });
        build_teacher_table(server_url);
        $('#register-teacher').on('click', function () { to_register_taecher(); });
        $('#cancel-teacher').on('click', function () { clear_teacher(); });
        $('#save-teacher').on('click', function () { save_teacher(server_url) });
    });
}());

function build_teacher_table(server_url) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/teacher/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': 1 },
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
                    teacher_gender_td.text(obj['gender']);
                    let teacher_telephone_td = $('<td></td>');
                    teacher_telephone_td.text(obj['telephone']);
                    let teacher_admission_date_td = $('<td></td>');
                    teacher_admission_date_td.text(obj['admissionDate']);
                    let teacher_status_td = $('<td></td>');
                    let teacher_status = '在职';
                    if (obj['status'] === 1) {
                        teacher_status = '离职';
                    }
                    teacher_status_td.text(teacher_status);
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
                build_teacher_table_pagination(1, result['totalPage']);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function build_teacher_table_pagination(currentPage, totalPage) {
    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#teacher-pagination').append(previous_li);

    // if (currentPage === 1) {
    //     for (let i = 0; i < 5; i++) {
    //         let page_link = $('<a class=\'page-link\'></a>');
    //         page_link.text(i + 1);
    //         page_link.on('click', function () { })
    //         let page_li = $('<li class=\'page-item\'></li>');
    //         page_li.append(page_link);
    //         $('#teacher-pagination').append(page_li);
    //     }
    // } else if (currentPage === totalPage) {
    // } else {
    // }



    for (let i = 2; i >= 0; i--) {
        let page_link = $('<a class=\'page-link\'></a>');
        page_link.text(currentPage - 1);
        page_link.on('click', function () { })
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#teacher-pagination').append(page_li);
    }

    let page_link = $('<a class=\'page-link\'></a>');
    page_link.text(currentPage);
    page_link.on('click', function () { })
    let page_li = $('<li class=\'page-item\'></li>');
    page_li.append(page_link);
    $('#teacher-pagination').append(page_li);

    for (let i = 0; i > 2; i++) {
        let page_link = $('<a class=\'page-link\'></a>');
        page_link.text(currentPage + 1 + i);
        page_link.on('click', function () { })
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#teacher-pagination').append(page_li);
    }



    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { })
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#teacher-pagination').append(next_li);
}


function save_teacher(server_url) {
    console.log('save_teacher')
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
    let gender = $('#gender').val()
    let status = $('#status').val()
    data['gender'] = gender;
    data['status'] = status;
    console.log(data);
    let action = $('action').val();

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
                $('#eidt-teacher-modal').modal({ 'show': false });
                build_teacher_table(server_url);

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
        data: { 'no': teacher_no },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                let teacher = data[0];
                $('#id').val(teacher['id']);
                $('#name').val(teacher['name']);
                $('#admission-date').val(teacher['admissionDate']);
                $('#resign-date').val(teacher['resignDate']);
                $('#telephone').val(teacher['telephone']);
                $('#gender').val(teacher['gender']);
                $('#status').val(teacher['status']);
                $('#action').val('update');
                $('#eidt-teacher-modal').modal({ 'show': true });
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