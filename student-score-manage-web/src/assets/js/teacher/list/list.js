(function () {
    'use strict';
    var serverUrl = '';

    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {
        $('#add-teacher-modal').load('/src/pages/teacher/update/update.html');
        $('#add-teacher-modal').modal({ 'show': false });
        $('#add-teacher').on('click', function () {
            $('#add-teacher-modal').modal({ 'show': true });
        });

        $.ajax({
            url: serverUrl + '/teacher/query',
            type: 'get',
            async: false,
            contentType: 'application/json',
            data: { 'pageSize': 10, 'currentPage': 1 },
            success: function (result) {
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    $.each(data, function (index, obj) {
                        const teacherNoTd = $("<td></td>");
                        teacherNoTd.text(obj['no']);
                        const teacherNameTd = $("<td></td>");
                        teacherNameTd.text(obj['name']);
                        const teacherGenderTd = $("<td></td>");
                        teacherGenderTd.text(obj['gender']);
                        const teacherIdentityNoTd = $("<td></td>");
                        teacherIdentityNoTd.text(obj['identityNo']);
                        const teacherTelephoneTd = $("<td></td>");
                        teacherTelephoneTd.text(obj['telephone']);
                        const teacherStatusTd = $("<td></td>");
                        teacherStatusTd.text(obj['status']);

                        const tr = $("<tr></tr>");
                        tr.append(teacherNoTd);
                        tr.append(teacherNameTd);
                        tr.append(teacherGenderTd);
                        tr.append(teacherIdentityNoTd);
                        tr.append(teacherTelephoneTd);
                        tr.append(teacherStatusTd);
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