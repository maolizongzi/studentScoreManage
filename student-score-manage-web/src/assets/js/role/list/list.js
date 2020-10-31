(function () {
    'use strict';
    let server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#add-role').on('click', function () { to_add_role(); });
        $('#save-role').on('click', function () { save_role(server_url); });
        $('#cancel-role').on('click', function () { clear_role_modal() });
        build_role_table(server_url, 1);
    });
}());

function build_role_table(server_url, current_page) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/role/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let tr = $('<tr></tr>');
                    let role_no_td = $('<td></td>');
                    role_no_td.text(obj['no']);
                    let role_name_td = $('<td></td>');
                    role_name_td.text(obj['name']);
                    let option_td = $('<td></td>')
                    let edit_button = $('<button>编辑</button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_role(server_url, obj['id']); });
                    option_td.append(edit_button);
                    tr.append(role_no_td);
                    tr.append(role_name_td);
                    tr.append(edit_button);
                    $('#table-content').append(tr);
                });
                build_role_table_pagination(current_page, result['totalPage'], server_url);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function clear_role_modal() {
    $('#id').val('');
    $('#no').val('');
    $('#name').val('');
    $('#action').val('add');

}

function to_edit_role(server_url, id) {
    clear_role_modal();
    $.ajax({
        url: server_url + '/role/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'id': id, 'pageSize': 1, 'currentPage': 1 },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === "00") {
                let role = data[0];
                $('#id').val(role['id']);
                $('#no').val(role['no']);
                $('#name').val(role['name']);
                $('#action').val('update');
                $('#edit-role-modal').modal({ 'show': true });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function to_add_role(server_url) {
    clear_role_modal();
    $('#edit-role-modal').modal('show');

}

function save_role(server_url) {
    var api_url = server_url + '/role/add';
    if ($('#action').val() === 'edit') {
        api_url = server_url + '/role/update';
    }


    let name = $('#name').val();
    let id = $('#id').val();
    let no = $('#no').val();

    let data = { 'name': name, 'id': id, 'no': no };
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
                $('#edit-role-modal').modal('hide');
                build_role_table(server_url, 1);
            }
        },
        error: function () {
            $.bootstrapGrowl('保存失败', { type: 'danger' });
        }
    });
}

function build_role_table_pagination(current_page, total_page, server_url) {
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
        page_link.on('click', function () { build_role_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#role-pagination').append(page_li);
    }
    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_role_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#role-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_role_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#role-pagination').append(next_li);
}