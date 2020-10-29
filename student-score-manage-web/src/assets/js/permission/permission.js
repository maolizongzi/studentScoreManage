(function () {
    'use strict';
    var server_url = '';
    $.when($.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        success: function (result) {
            server_url = result['server-url'];
        }
    })).done(function () {
        $.ajax({
            type: 'get',
            url: '/config/menu.json',
            dataType: 'json',
            success: function (menu_content) {
                $('#save-role-permission').on('click', function () { save_role_permission(server_url); });
                build_role_select(server_url)
                build_permission_menu($('#menu-permission'), menu_content['menu-item-data']);
            }
        });
    }
    );
}());

function build_role_select(server_url) {
    $('#role-select').html('');
    $.ajax({
        url: server_url + '/role/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 100, 'currentPage': 1 },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let role_option = $('<option></option>');
                    role_option.text(obj['name']);
                    role_option.val(obj['id']);
                    $('#role-select').append(role_option);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function build_permission_menu(item, menu_content) {
    $.each(menu_content, function (index, menu_item) {
        let menu_permissions = menu_item['permissions'];
        let menu_item_div = $('<div class=\'nav-link form-check form-check-inline\'></div>');
        let checkbox_id = 'box' + window.performance.now();
        let permission_checkbox = $('<input />');
        permission_checkbox.attr({ type: 'checkbox', class: 'form-check-input', id: checkbox_id, 'value': menu_permissions['menu'] });
        let permission_text = $('<label></label>');
        permission_text.attr({ class: 'form-check-label', for: checkbox_id });
        permission_text.text(menu_item['name']);
        menu_item_div.prepend(permission_checkbox);
        menu_item_div.append(permission_text);
        let menu_li = $('<li class=\'nav-item\'></li>');
        menu_li.append(menu_item_div);
        item.append(menu_li);


        let child_id = 'child' + window.performance.now();
        child_id = child_id.replace('\.', '');
        menu_item_div.attr({
            'href': '#' + child_id,
            'data-toggle': 'collapse',
            'aria-expanded': 'false',
            'class': 'dropdown-toggle nav-link form-check form-check-inline'
        });
        let child_ul = $('<ul></ul>');
        child_ul.attr({
            id: child_id,
            class: 'collapse list-unstyled',
            style: 'padding-left: 20px'
        });

        let operation_permissions = menu_permissions['operation'];
        $.each(operation_permissions, function (index, operation_permission) {
            let operation_permission_li = build_permission_li(operation_permission);
            child_ul.append(operation_permission_li);
        });


        if (menu_item['child']) { build_permission_menu(child_ul, menu_item['child']); }
        menu_li.append(child_ul);

    });
}


function build_permission_li(operation_permission) {
    let menu_item_div = $('<div class=\'nav-link form-check form-check-inline\'></div>');
    let checkbox_id = 'box' + window.performance.now();
    let permission_checkbox = $('<input />');
    permission_checkbox.attr({ type: 'checkbox', class: 'form-check-input', id: checkbox_id, value: operation_permission['permission'] });
    let permission_text = $('<label></label>');
    permission_text.attr({ class: 'form-check-label', for: checkbox_id });
    permission_text.text(operation_permission['name']);
    menu_item_div.prepend(permission_checkbox);
    menu_item_div.append(permission_text);
    let menu_li = $('<li class=\'nav-item\'></li>');
    menu_li.append(menu_item_div);
    return menu_li;

}

function save_role_permission(server_url) {
    let role_id = $('#role-select').val();
    let permission_input_arr = $('#menu-permission :checked');
    let permissions = [];
    $.each(permission_input_arr, function (index, obj) {
        permissions.push($(obj).val());
    });
    let role_permission = {};
    role_permission['roleId'] = role_id;
    role_permission['permissions'] = permissions;
    $.ajax({
        url: server_url + '/role/permission/add',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(role_permission),
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
            }
        }
    });
}