(function () {
    'use strict';
    let server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#birthday').datepicker({ language: 'zh-CN' });
        $('#admission-date').datepicker({ language: 'zh-CN' });
        $('#graduation-date').datepicker({ language: 'zh-CN' });
        $('#register-student').on('click', function () { to_register_student(); });
        $('#cancel-student').on('click', function () { clear_student(); });
        $('#save-student').on('click', function () { save_student(server_url); })
        build_student_table(server_url, 1);
        build_classes_select(server_url, 1);
    });
}());


function build_student_table(server_url, current_page) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/student/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let tr = $('<tr></tr>');
                    let student_no_td = $('<td></td>');
                    student_no_td.text(obj['no']);
                    let student_name_td = $('<td></td>');
                    student_name_td.text(obj['name']);
                    let gender_td = $('<td></td>');
                    gender_td.text(obj['gender'] === '0' ? '男' : '女');
                    let identity_no_td = $('<td></td>');
                    identity_no_td.text(obj['identityNo']);
                    let borthday_td = $('<td></td>');
                    borthday_td.text(obj['birthday']);
                    let status_td = $('<td></td>');
                    let status_text = '未毕业';
                    if (obj['status'] === '1') { status_text = '毕业'; } else if (obj['status'] === '2') { status_text = '退学'; }
                    status_td.text(status_text);
                    let option_td = $('<td></td>')
                    let edit_button = $('<button>编辑</button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_student(server_url, obj['id']); });
                    option_td.append(edit_button);
                    tr.append(student_no_td);
                    tr.append(student_name_td);
                    tr.append(gender_td);
                    tr.append(identity_no_td);
                    tr.append(borthday_td);
                    tr.append(status_td);
                    tr.append(option_td);
                    $('#table-content').append(tr);
                });
                build_student_table_pagination(current_page, result['totalPage']);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function save_student(server_url) {
    let input_arr = $('#student-data input');
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
    let classes = $('#classes').val();
    data['gender'] = gender;
    data['status'] = status;
    data['classesId'] = classes;
    console.log(data);
    let api_url = server_url + '/student/register';
    if ($('#action').val() === 'update') {
        api_url = server_url + '/student/update';
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
                $('#edit-student-modal').modal('hide');
                build_student_table(server_url, 1);
            }
        },
        error: function () {
            $.bootstrapGrowl('保存失败', { type: 'danger' });
        }
    });
}

function clear_student() {
    $('#no').val('');
    $('#name').val('');
    $('#admission-date').val('');
    $('#resign-date').val('');
    $('#telephone').val('');
    $('#action').val('register');
}

function to_edit_student(server_url, id) {
    clear_student();
    $.ajax({
        url: server_url + '/student/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': id, 'pageSize': 1, 'currentPage': 1 },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === "00") {
                let student = data[0];
                $('#id').val(student['id']);
                $('#no').val(student['no']);
                $('#name').val(student['name']);
                $('#identity-no').val(student['identityNo']);
                $('#address').val(student['address']);
                $('#birthday').val(student['birthday']);
                $('#admission-date').val(student['admissionDate']);
                $('#graduation-date').val(student['graduationDate']);
                $('#telephone').val(student['telephone']);
                $('#gender').val(student['gender']);
                $('#classes').val(student['classesId']);
                $('#status').val(student['status']);
                $('#action').val('update');
                $('#edit-student-modal').modal({ 'show': true });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function to_register_student() {
    clear_student();
    $('#edit-student-modal').modal({ 'show': true });
}

function build_classes_select(server_url, current_page) {
    $('#classes').html('');
    $.ajax({
        url: server_url + '/classes/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 20, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let classes_option = $('<option></option>');
                    classes_option.text(obj['name']);
                    classes_option.val(obj['id']);
                    $('#classes').append(classes_option);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });

}

function build_student_table_pagination(current_page, total_page, server_url) {
    $('#student-pagination').html('');
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
        page_link.on('click', function () { build_student_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#student-pagination').append(page_li);
    }
    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_student_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#student-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_student_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#student-pagination').append(next_li);
}