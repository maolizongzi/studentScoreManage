(function () {
    var server_url = '';
    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {

        $('#admission-date').datepicker({ language: 'zh-CN' });
        $('#resign-date').datepicker({ language: 'zh-CN' });
        build_table(server_url);
        $('#register-teacher').on('click', function () { to_register_taecher(); });
        $('#cancel-teacher').on('click', function () { clear_teacher(); });
        $('#save-teacher').on('click', function () { save_teacher(server_url) });
    });
}());

function build_table(server_url) {
    $.ajax({
        url: server_url + '/teacher/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    const teacher_no_td = $('<td></td>');
                    teacher_no_td.text(obj['no']);
                    const teacher_name_td = $('<td></td>');
                    teacher_name_td.text(obj['name']);
                    const teacher_gender_td = $('<td></td>');
                    teacher_gender_td.text(obj['gender']);
                    const teacher_telephone_td = $('<td></td>');
                    teacher_telephone_td.text(obj['telephone']);
                    const teacher_admission_date_td = $('<td></td>');
                    teacher_admission_date_td.text(obj['admissionDate']);
                    const teacher_status_td = $('<td></td>');
                    var teacher_status = '在职';
                    if (obj['status'] === 1) {
                        teacher_status = '离职';
                    }
                    teacher_status_td.text(teacher_status);
                    const option_td = $('<td></td>');
                    const edit_teacher_btn = $('<button>编辑</button>');
                    edit_teacher_btn.attr({ 'class': 'btn btn-outline-primary btn-sm' })
                    edit_teacher_btn.on('click', function () { to_edit_teacher(server_url, obj['no']) });
                    option_td.append(edit_teacher_btn);

                    const tr = $('<tr></tr>');
                    tr.append(teacher_no_td);
                    tr.append(teacher_name_td);
                    tr.append(teacher_gender_td);
                    tr.append(teacher_telephone_td);
                    tr.append(teacher_admission_date_td);
                    tr.append(teacher_status_td);
                    tr.append(option_td);
                    $('#table-content').append(tr);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}


function save_teacher(server_url) {
    console.log('save_teacher')
    var input_arr = $('#teacher-data input');
    var data = {}
    $.each(input_arr, function (index, obj) {
        var required_attr = $(obj).attr('required');
        const input_name = $(obj).attr('name');
        var inputVal = $(obj).val();
        if (required_attr === 'required') {
            if (inputVal === '') {
                console.log('val null');
            }
        }
        data[input_name] = inputVal;
    });
    const gender = $('#gender').val()
    const status = $('#status').val()
    data['gender'] = gender;
    data['status'] = status;
    console.log(data);
    var action = $('action').val();

    $.ajax({
        url: server_url + '/teacher/' + action,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $('#eidt-teacher-modal').modal({ 'show': false });
            }
        },
        error: function (err) {
            console.log(err);
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
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                const teacher = data[0];
                $('#name').val(teacher['name']);
                $('#admission-date').val(teacher['admissionDate']);
                $('#resign-date').val(teacher['resignDate']);
                $('#telephone').val(teacher['telephone']);
                $('#gender').val(teacher['gender']);
                $('#status').val(teacher['status']);
                $('#action').val('update');
                $('#eidt-teacher-modal').modal({ 'show': true });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function clear_teacher() {
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