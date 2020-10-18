(function () {
    'use strict';
    var server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#add-classes').on('click', function () { to_add_modal(); });
        $('#save-classes').on('click', function () { save_classes(server_url) });
        $('#cancel-classes').on('click', function () { clear_classes_modal(server_url) });

        $.ajax({
            url: server_url + '/classes/query',
            type: 'get',
            contentType: 'application/json',
            data: { 'pageSize': 10, 'currentPage': 1 },
            success: function (result) {
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    $.each(data, function (index, obj) {
                        const classes_no_td = $('<td></td>');
                        classes_no_td.text(obj['no']);
                        const classes_name_td = $('<td></td>');
                        classes_name_td.text(obj['name']);
                        const classes_grade_td = $('<td></td>');
                        classes_grade_td.text(obj['grade']);
                        const edit_button = $('<button>编辑</button>');
                        edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                        edit_button.on('click', function () { to_edit_modal(server_url, obj['id']); });
                        const operation_td = $('<td></td>');
                        operation_td.append(edit_button);
                        const tr = $('<tr></tr>');
                        tr.append(classes_no_td);
                        tr.append(classes_name_td);
                        tr.append(classes_grade_td);
                        tr.append(operation_td);
                        $('#table-content').append(tr);
                    });
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
}());

function clear_classes_modal() {
    $('#id').val('');
    $('#no').val('');
    $('#name').val('');
    $('#action').val('add');
}

function to_add_modal() {
    clear_classes_modal();
    $("#edit-classes-modal").modal({ 'show': true });
}

function to_edit_modal(server_url, classes_id) {
    $.ajax({
        url: server_url + '/classes/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': classes_id, 'pageSize': 1, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                classes = data[0];
                $('#id').val(classes['id']);
                $('#no').val(classes['no']);
                $('#name').val(classes['name']);
                $('#grade').val(classes['grade']);
                $('#action').val('edit');
                $("#edit-classes-modal").modal({ 'show': true });
            }
        }
    });
}

function save_classes(server_url) {
    api_url = server_url + '/classes/add';
    if ($('#action').val() === 'edit') {
        api_url = server_url + '/classes/update';
    }

    let id = $('#id').val(classes['id']);
    let no = $('#no').val(classes['no']);
    let name = $('#name').val(classes['name']);
    let grade = $('#grade').val(classes['grade']);

    $.ajax({
        url: server_url + '/classes/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': id, 'no': no, 'name': name, 'grade': grade },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') { }
        }
    });
}