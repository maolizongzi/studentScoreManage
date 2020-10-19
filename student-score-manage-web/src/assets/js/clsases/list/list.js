(function () {
    'use strict';
    let server_url = '';

    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#add-classes').on('click', function () { to_add_modal(); });
        $('#save-classes').on('click', function () { save_classes(server_url) });
        $('#cancel-classes').on('click', function () { clear_classes_modal(server_url) });
        build_classes_table(server_url, 1);
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
            let code = result['code'];
            let data = result['data'];
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

    let id = $('#id').val();
    let no = $('#no').val();
    let name = $('#name').val();
    let grade = $('#grade').val();

    $.ajax({
        url: api_url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({ 'id': id, 'no': no, 'name': name, 'grade': grade }),
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.bootstrapGrowl('保存成功', { type: 'success' });
                $("#edit-classes-modal").modal('hide');
                build_classes_table(server_url, 1);
            }
        }
    });
}

function build_classes_table_pagination(current_page, total_page, server_url) {
    $('#classes-pagination').html('');
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
        page_link.on('click', function () { build_classes_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#classes-pagination').append(page_li);
    }



    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_classes_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#classes-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_classes_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#classes-pagination').append(next_li);
}

function build_classes_table(server_url, current_page) {
    $('#table-content').html('');
    $.ajax({
        url: server_url + '/classes/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let classes_no_td = $('<td></td>');
                    classes_no_td.text(obj['no']);
                    let classes_name_td = $('<td></td>');
                    classes_name_td.text(obj['name']);
                    let classes_grade_td = $('<td></td>');
                    classes_grade_td.text(obj['grade']);
                    let edit_button = $('<button>编辑</button>');
                    edit_button.attr({ 'class': 'btn btn-outline-primary btn-sm' });
                    edit_button.on('click', function () { to_edit_modal(server_url, obj['id']); });
                    let operation_td = $('<td></td>');
                    operation_td.append(edit_button);
                    let tr = $('<tr></tr>');
                    tr.append(classes_no_td);
                    tr.append(classes_name_td);
                    tr.append(classes_grade_td);
                    tr.append(operation_td);
                    $('#table-content').append(tr);
                });
                build_classes_table_pagination(current_page, result['totalPage'], server_url);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });

}