(function () {
    'use strict';
    var serverUrl = '';

    $.load('/config/config.json', '', function (data) {
        debugger;
        console.log(data);
    });

    $.ajax({
        url: serverUrl + '/subject/query',
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
                    const subjectNoTd = $("<td></td>");
                    subjectNoTd.text(obj['no']);
                    const subjectNameTd = $("<td></td>");
                    subjectNameTd.text(obj['name']);

                    const tr = $("<tr></tr>");
                    tr.append(subjectNoTd);
                    tr.append(subjectNameTd);
                    $("#table-content").append(tr);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}());