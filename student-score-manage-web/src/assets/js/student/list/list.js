(function () {
    'use strict';
    var serverUrl = '';
    $.ajax({
        type: 'get',
        url: '/config/config.json',
        dataType: 'json',
        async: false,
        success: function (result) {
            serverUrl = result['server-url'];
        }
    });

    $.load('/config/config.json', '', function (data) {
        debugger;
        console.log(data);
    });

    $.ajax({
        url: serverUrl + '/student/query',
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
                    // const studentNoTd = $("<td></td>");
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
}());