(function () {
    'use strict';
    var serverUrl = '';
    
 $(document).ready(function(){
        var dom = document.getElementById("lineMain");
        var myChart = echarts.init(dom);
        myChart.clear();
        // 使用刚指定的配置项和数据显示图表。
        
    $.when($.getJSON('/config/config.json', function (result) {
        serverUrl = result['server-url'];
    })).done(function () {
        $.ajax({
            url: serverUrl + '/exam/score/lineChart',
            type: 'get',
            async: false,
            contentType: 'application/json',
            data: { 'pageSize': 2, 'currentPage': 1 },
            success: function (result) {
                var   option = {
                    title: {
                        text: '折线图堆叠',
                        left: 'right'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['2019届1班', '2019届2班', '2019届3班', '2019届4班']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data:  ['不及格率', '及格率', '中等率', '优秀率']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: result
                };
                 myChart.setOption(option, true);
            },
            error: function (err) {
                console.log(err);
            }
         });
        });
    })
}());


  
