(function () {
    'use strict';
    var serverUrl = '';

    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {

        $.ajax({
            url: serverUrl + '/classes/query',
            type: 'get',
            async: false,
            contentType: 'application/json',
            data: { 'pageSize': 10, 'currentPage': 1 },
            success: function (result) {
                debugger;
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    $.each(data, function (index, obj) {
                        const classesNoTd = $('<td></td>');
                        classesNoTd.text(obj['no']);
                        const classesNameTd = $('<td></td>');
                        classesNameTd.text(obj['name']);
                        const classesGradeTd = $('<td></td>');
                        classesGradeTd.text(obj['grade']);

                        const tr = $('<tr></tr>');
                        tr.append(classesNoTd);
                        tr.append(classesNameTd);
                        tr.append(classesGradeTd);
                        $('#table-content').append(tr);
                    });
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
}());