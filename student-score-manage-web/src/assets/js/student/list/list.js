(function () {
    'use strict';
    var server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#birthday').datepicker({ language: 'zh-CN' });
        $('#admission-date').datepicker({ language: 'zh-CN' });
        $('#graduation-date').datepicker({ language: 'zh-CN' });
        $('#register-student').on('click', function () { $('#edit-student-modal').modal({ 'show': true }); });
        build_student_table(server_url);
    });
}());


function build_student_table(server_url) {
    $.ajax({
        url: server_url + '/student/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    const tr = $('<tr></tr>');
                    const student_no_td = $('<td></td>');
                    student_no_td.text(obj['no']);
                    const student_name_td = $('<td></td>');
                    student_name_td.text(obj['name']);
                    const gender_td = $('<td></td>');
                    gender_td.text(obj['gender']);
                    const identity_no_td = $('<td></td>');
                    identity_no_td.text(obj['identityNo']);
                    const borthday_td = $('<td></td>');
                    borthday_td.text(obj['birthday']);
                    const status_td = $('<td></td>');
                    status_td.text(obj['status']);
                    const option_td = $('<td></td>')
                    const edit_button = $('<button>编辑</button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_student(server_url, obj['no']); })
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
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                const student = data[0];
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