(function () {
    'use strict';
    var server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#add-subject').on('click', function () { to_add_modal(); });
        $('#cancel-subject').on('click', function () { clear_subject_modal(); });
        $('#save-subject').on('click', function () { save_subject(server_url); })
        build_subject_table(server_url);
    });
}());

function build_subject_table(server_url) {
    $.ajax({
        url: server_url + '/subject/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    const subject_no_td = $("<td></td>");
                    subject_no_td.text(obj['no']);
                    const subject_name_td = $("<td></td>");
                    subject_name_td.text(obj['name']);
                    const edit_button = $('<button></button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_modal(server_url, obj['id']) });
                    const operation_td = $('<td></td>');
                    operation_td.append(edit_button);

                    const tr = $("<tr></tr>");
                    tr.append(subject_no_td);
                    tr.append(subject_name_td);
                    tr.append(operation_td);

                    $("#table-content").append(tr);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function save_subject(server_url) {
    var api_url = server_url + '/subject/add';
    if ($('#action').val() === 'edit') {
        api_url = server_url + 'subject/update';
    }
    var name = $('#name').val();
    var no = $('#no').val();
    var id = $('id').val()


    $.ajax({
        url: api_url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({ 'name': name, 'no': no, 'id': id }),
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') { 
                
            }
        }
    });

}

function to_add_modal() {
    clear_subject_modal();
    $('#edit-subject-modal').modal({ 'show': true });
}

function to_edit_modal(server_url, id) {
    clear_subject_modal();
    $.ajax({
        url: server_url + '/subject/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': id, 'pageSize': 10, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                const subject = data[0];
                $("#no").val(subject_name_td['no']);
                $('#name').val(subject_name_td['name']);
                $('#action').val('edit');
                $('#edit-subject-modal').modal({ 'show': true });
            }
        }
    });
}

function clear_subject_modal() {
    $("#no").val('');
    $('#name').val('');
    $('#action').val('add');
}