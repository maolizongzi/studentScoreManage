(function () {
    'use strict';
    var serverUrl = '';

    $.when($.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        success: function (result) {
            serverUrl = result['server-url'];
        }
    })).then(function () {
        $('#sign-in').click(function () {
            var no = $('#inputNo').val();
            var password = $('#inputPassword').val();
            $.ajax({
                url: serverUrl + '/teacher/login',
                type: 'post',
                async: false,
                contentType: 'application/json',
                data: JSON.stringify({ 'no': no, 'password': password }),
                success: function (result) {
                    var code = result['code'];
                    var data = result['data'];
                    if (code === '00') {
                        window.location.href = "/src/layouts/layout.html"
                    }
                },
                error: function (err) {
                    console.log(err);
                    console.error(err);
                }
            });
        });
    });
}());