(function () {
    'use strict';
    var serverUrl = '';
    var menu = [{}];
    $.when($.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        success: function (result) {
            serverUrl = result['server-url'];
        }
    })).then(
        $.ajax({
            type: 'get',
            url: '/config/menu.json',
            dataType: 'json',
            success: function (menuContent) {
                menu = menuContent['menu-item-data'];
                var ul = $('<ul></ul>');
                ul.attr({ class: "nav flex-column mb-2" });
                $.each(menu, function (index, obj) {
                    var li = $('<li></li>');
                    li.attr({ class: 'nav-item' });
                    var a = $('<a></a>');
                    a.text(obj['name']);
                    a.attr({ class: 'nav-link', href: obj['path'] });
                    var span = $('<span></span>');
                    if (obj['icon']) {
                        span.attr({ 'data-feather': obj['icon'] });
                    }
                    a.prepend(span);
                    li.append(a);
                    ul.append(li);
                    $('#menuBar').append(ul);
                });
                feather.replace();
            }
        })
    );
}());