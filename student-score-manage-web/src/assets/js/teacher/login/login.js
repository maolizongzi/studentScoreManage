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
        localStorage.clear();
        $('#sign-in').click(function () {
            var no = $('#inputNo').val();
            var password = $('#inputPassword').val();
            $.ajax({
                url: server_url + '/teacher/login',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({ 'no': no, 'password': password }),
                success: function (result) {
                    var code = result['code'];
                    var data = result['data'];
                    if (code === '00') {
                        let permissions = data['permissions'];
                        localStorage.setItem('permissions', permissions);
                        window.location.href = "/src/layouts/layout.html";
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });
        });
    });
}());


function save_permission(permiisons) {

}