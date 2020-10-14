(function () {
    'use strict';
    var serverUrl = '';

    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {

        $.ajax({
            url: serverUrl + '/student/query',
            type: 'get',
            contentType: 'application/json',
            data: { 'pageSize': 10, 'currentPage': 1 },
            success: function (result) {
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    $.each(data, function (index, obj) {
                        const tr = $("<tr></tr>");
                        const studentNoTd = $("<td></td>");
                        studentNoTd.text(obj['no']);
                        const studentNameTd = $("<td></td>");
                        studentNoTd.text(obj['name']);
                        const genderTd = $("<td></td>");
                        genderTd.text(obj['gender']);
                        const identityNoTd = $("<td></td>");
                        identityNoTd.text(obj['identityNo']);
                        const borthdayTd = $("<td></td>");
                        borthdayTd.text(obj['birthday']);
                        const statusTd = $("<td></td>");
                        statusTd.text(obj['status']);
                        tr.append(studentNoTd);
                        tr.append(studentNameTd);
                        tr.append(genderTd);
                        tr.append(identityNoTd);
                        tr.append(borthdayTd);
                        tr.append(statusTd);
                        $("#table-content").append(tr);
                    });
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
}());