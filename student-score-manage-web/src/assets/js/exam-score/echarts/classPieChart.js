(function () {
    'use strict';
    var serverUrl = '';
    
 $(document).ready(function(){
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        myChart.clear();
        // 使用刚指定的配置项和数据显示图表。
        
    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {
        $.ajax({
            url: serverUrl + '/exam/score/pieChart',
            type: 'get',
            async: false,
            contentType: 'application/json',
            data: { 'pageSize': 2, 'currentPage': 1 },
            success: function (result) {
                debugger;
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                 var   option={
                        title: {
                            text: '考试成绩显示图',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c} ({d}%)'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data:['直接访问1', '邮件营销1', '联盟广告1', '视频广告1']
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: '55%',
                                center: ['40%', '60%'],
                                data: [
                                    {value: 335, name: '不及格率'},
                                    {value: 310, name: '及格率'},
                                    {value: 234, name: '中等率'},
                                    {value: 135, name: '优秀率'}
                                ],
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                     };
                     myChart.setOption(option, true);
                     
                     var mychartOption=myChart.getOption();
                     mychartOption.legend[0].data=['不及格率', '及格率', '中等率', '优秀率'];
                     myChart.setOption(mychartOption,true);

                }
            },
            error: function (err) {
                console.log(err);
            }
         });
        });
    })

    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {
        $.ajax({
            url: serverUrl + '/exam/score/query',
            type: 'get',
            async: false,
            contentType: 'application/json',
            data: { 'pageSize': 2, 'currentPage': 1 },
            success: function (result) {
                debugger;
                var code = result['code'];
                var data = result['data'];
                if (code === '00') {
                    $.each(data, function (index, obj) {
                        const studentNoTd = $("<td></td>");
                        studentNoTd.text(obj['studentNo']);

                        const studentNameTd = $("<td></td>");
                        studentNameTd.text(obj['studentName']);

                        const subjectNameTd = $("<td></td>");
                        subjectNameTd.text(obj['subjectName']);

                        const examNameTd = $("<td></td>");
                        examNameTd.text(obj['examName']);
                        const examScoreTd = $("<td></td>");
                        examScoreTd.text(obj['examScore']);
                        const examDateTd = $("<td></td>");
                        examDateTd.text(obj['examDate']);
                        const classesNameTd = $("<td></td>");
                        classesNameTd.text(obj['classesName']);

                         
                        const tr = $("<tr></tr>");
                        tr.append(studentNoTd);
                        tr.append(studentNameTd);
                        tr.append(subjectNameTd);
                        tr.append(examNameTd);
                        tr.append(examScoreTd);
                        tr.append(examDateTd);
                        tr.append(classesNameTd);
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


  
