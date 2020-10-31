(function () {
    'use strict';
    var server_url = '';
    // var menu = [{}];
    $.when($.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        success: function (result) {
            server_url = result['server-url'];
        }
    })).done(function () {
        $('#sign-out').on('click', function () { sign_out(); });
        $.ajax({
            type: 'get',
            url: '/config/menu.json',
            dataType: 'json',
            success: function (menu_content) {
                let permissions = localStorage.getItem('permissions');
                build_sidebar_menu($('#sidebar-menu'), menu_content['menu-item-data'], permissions);
                feather.replace();
            }
        });
    }
    );
}());

function sign_out() {
    localStorage.clear();
    location.href = '/src/pages/teacher/login/login.html';
}

function build_sidebar_menu(item, menu_content, permissions) {
    $.each(menu_content, function (index, menu_item) {
        let menu_permission = menu_item['permissions']['menu'];
        if (!permission_exists(menu_permission, permissions)) {
            return true;
        }
        let menu_link = $('<a class=\'nav-link\'></a>');
        menu_link.text(menu_item['name']);
        if (!menu_item['child'] && menu_item['path'] != '') {
            menu_link.on('click', function () {
                $('#page-title').text();
                $('#page-title').text(menu_item['name']);
                $('#content').empty();
                $('#content').load(menu_item['path']);
            });
        }

        let menu_icon = $('<span></span>');
        menu_icon.attr({ 'data-feather': menu_item['icon'] === '' ? 'book' : menu_item['icon'] });
        menu_link.prepend(menu_icon);
        let menu_li = $('<li class=\'nav-item\'></li>');
        menu_li.append(menu_link);
        item.append(menu_li);
        if (menu_item['child']) {
            let timestamp = new Date().getTime();
            let child_id = 'child' + timestamp;
            menu_link.attr({
                'href': '#' + child_id,
                'data-toggle': 'collapse',
                'aria-expanded': 'false',
                'class': 'dropdown-toggle nav-link'
            });
            let child_ul = $('<ul></ul>');
            child_ul.attr({
                id: child_id,
                class: 'collapse list-unstyled',
                style: 'padding-left: 20px'
            });
            build_sidebar_menu(child_ul, menu_item['child'], permissions);
            menu_li.append(child_ul);
        }

    });
}

function build_sidebar_menu_bak() {
    menu = menuContent['menu-item-data'];
    var ul = $('<ul></ul>');
    ul.attr({ class: 'nav flex-column' });
    $.each(menu, function (index, obj) {
        var li = $('<li></li>');
        li.attr({ class: 'nav-item' });
        var a = $('<a></a>');
        a.text(obj['name']);
        a.attr({ class: 'nav-link' });
        var span = $('<span></span>');
        if (obj['icon']) {
            span.attr({ 'data-feather': obj['icon'] });
        }
        a.prepend(span);
        li.append(a);
        ul.append(li);
        $('#menuBar').append(ul);
        li.on('click', function () {
            $('#page-title').text();
            $('#page-title').text(obj['name']);
            $('#content').empty();
            $('#content').load(obj['path']);
        });
    });
}



function permission_exists(permission, permissions) {
    let flag = false;
    if (permissions) {
        $.each(permissions.split(','), function (index, obj) {
            if (obj === permission) {
                flag = true;
                return false;
            }
        });
    }
    return flag;
}