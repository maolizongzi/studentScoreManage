(function () {
    'use strict';
    var server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#add-subject').on('click', function () { to_add_modal(); });
        $('#cancel-subject').on('click', function () { clear_subject_modal(); });
        $('#save-subject').on('click', function () { save_subject(server_url); })
        build_subject_table(server_url, 1);
    });
}());

function build_subject_table(server_url, current_page) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/subject/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let subject_no_td = $("<td></td>");
                    subject_no_td.text(obj['no']);
                    let subject_name_td = $("<td></td>");
                    subject_name_td.text(obj['name']);
                    let edit_button = $('<button class=\'btn btn-outline-primary btn-sm\'>编辑</button>');
                    edit_button.on('click', function () { to_edit_modal(server_url, obj['id']) });
                    let operation_td = $('<td></td>');
                    operation_td.append(edit_button);
                    operation_td.append('&nbsp;&nbsp;&nbsp;&nbsp;');   

                    let delete_button = $('<button class=\'btn btn-outline-primary btn-sm\'>删除</button>');
                    delete_button.on('click', function () { to_delete_modal(server_url, obj['id']) });
                    let operationDelte_td = $('<td></td>');
                    operation_td.append(delete_button);


                    let tr = $("<tr></tr>");
                    tr.append(subject_no_td);
                    tr.append(subject_name_td);
                    tr.append(operation_td);
                    $("#table-content").append(tr);
                });
                build_subject_table_pagination(current_page, result['totalPage'], server_url);
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
        api_url = server_url + '/subject/update';
    }
    var name = $('#name').val();
    var no = $('#no').val();
    var id = $('#id').val();
    $.ajax({
        url: api_url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({ 'name': name, 'no': no, 'id': id }),
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $('#edit-subject-modal').modal('hide');
                build_subject_table(server_url, 1);
            }
        }
    });

}

function to_add_modal() {
    clear_subject_modal();
    $('#edit-subject-modal').modal({ 'show': true });
}

function to_delete_modal(server_url, id) {
    if(confirm("确实要删除吗？")){
        $.ajax({
            url: server_url + '/subject/delete',
            type: 'get',
            contentType: 'application/json',
            data: { 'id': id},
            success: function (result) {
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    alert("已经删除！");
                    build_subject_table(server_url, 1);
                }
            },
            error: function (err) {
                alert("删除失败！");
                console.log(err);
            }
        });
    }else {
        alert("已经取消了删除操作");
    }
}
function to_edit_modal(server_url, id) {
    clear_subject_modal();
    $.ajax({
        url: server_url + '/subject/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': id, 'pageSize': 1, 'currentPage': 1 },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                let subject = data[0];
                $("#id").val(subject['id']);
                $("#no").val(subject['no']);
                $('#name').val(subject['name']);
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

function build_subject_table_pagination(current_page, total_page, server_url) {
    debugger;
    $('#subject-pagination').html('');
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
        page_link.on('click', function () { build_subject_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#subject-pagination').append(page_li);
    }
    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_subject_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#subject-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_subject_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#subject-pagination').append(next_li);
}