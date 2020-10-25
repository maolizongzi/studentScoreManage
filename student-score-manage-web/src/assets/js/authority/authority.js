(function () {
    'use strict';
    let serverUrl = '';
    $.when($.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        success: function (result) {
            serverUrl = result['server-url'];
        }
    })).done(function () {
        $.ajax({
            type: 'get',
            url: '/config/menu.json',
            dataType: 'json',
            success: function (menu_content) {
                let tree_data = [];
                build_tree_data(tree_data, menu_content['menu-item-data']);
                $('#authority-tree').treeview({ data: tree_data });
            }
        });
    }
    );
}());

function build_tree_data(tree_data, menu_content) {
    $.each(menu_content, function (index, menu_item) {
        console.log(menu_item);
        let tree_item = { 'text': menu_item['name'] };
        tree_data.push(tree_item);
    });

}