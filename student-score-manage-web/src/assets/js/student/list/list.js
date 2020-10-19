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
        $('#cancel-student').on('click', function () { clear_student() });
        $('#save-student').on('click',function(){})
        build_student_table(server_url, 1);
        build_classes_select(server_url, 1);
    });
}());


function build_student_table(server_url, current_page) {
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
                    gender_td.text(obj['gender']);
                    let identity_no_td = $('<td></td>');
                    identity_no_td.text(obj['identityNo']);
                    let borthday_td = $('<td></td>');
                    borthday_td.text(obj['birthday']);
                    let status_td = $('<td></td>');
                    status_td.text(obj['status']);
                    let option_td = $('<td></td>')
                    let edit_button = $('<button>编辑</button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_student(server_url, obj['no']); });
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

function clear_student() {
    $('#no').val('');
    $('#name').val('');
    $('#admission-date').val('');
    $('#resign-date').val('');
    $('#telephone').val('');
    $('#action').val('register');
}

function to_edit_student(server_url, student_no) {
    clear_student();
    $.ajax({
        url: server_url + '/student/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'no': student_no, 'pageSzie': 1, 'currentPage': 1 },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                let student = data[0];
                $('#name').val(student['name']);
                $('#admission-date').val(student['admissionDate']);
                $('#graduation-date').val(student['graduationDate']);
                $('#telephone').val(student['telephone']);
                $('#gender').val(student['gender']);
                $('#status').val(student['status']);
                $('#action').val('update');
                $('#eidt-student-modal').modal({ 'show': true });
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