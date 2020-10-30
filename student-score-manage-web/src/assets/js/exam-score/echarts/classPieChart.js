(function () {
    'use strict';
    let server_url = '';
    $.when($.getJSON('/config/config.json', function (result) {
        server_url = result['server-url'];
    })).done(function () {
        $('#birthday').datepicker({ language: 'zh-CN' });
        $('#examDateId').datepicker({ language: 'zh-CN' });
        //打开新增页面
        $('#add-examScore').on('click', function () { add_examScore(server_url,1); });
        //保存
        $('#save-examScore').on('click', function () { 
             save_examScore(server_url+'/exam/score/add'); 
        });
        //下载
        $('#download-examScore').on('click', function () { 
            location.href =server_url + "/exam/score/downExamScore"  
        });
        //根据条件查询
        $('#to-exam').on('click', function () { to_query_exam(server_url, 1); });
       // build_examScore_table(server_url, 1);
       //绑定班级select
        build_classes_select('classesId',server_url, 1);
        //绑定科目select
        build_subject_select('subjectNameId',server_url, 1);
        //绑定考试slect
        build_exam_select('examNameId',server_url, 1);
    });
}());
var dom;
var myChart ;
$(document).ready(function(){
     dom = document.getElementById("main");
     myChart = echarts.init(dom);
    myChart.clear();
    // 使用刚指定的配置项和数据显示图表。
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
            data:['不及格人数', '及格人数', '中等人数', '优秀人数']
        },
        series: [
            {
                type: 'pie',
                radius: '66%',
                center: ['50%', '60%'],
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
})
//加载饼图
function loadEchart(server_url){
    let classNameText = $('#classesId').find("option:selected").text();
    let subjectNameText = $('#subjectNameId').find("option:selected").text();
    let examNameText = $('#examNameId').find("option:selected").text();

    let classNOText = $('#classesId').val();
    let subjectNOText = $('#subjectNameId').val();
    let examNOText = $('#examNameId').val();

    let examDateText=$('#examDateId').val();

        $.ajax({
            url: server_url + '/exam/score/pieChart',
            type: 'get',
            async: false,
            data: {'className': classNameText,'classesNo': classNOText, 'subjectName': subjectNameText,'subjectNo': subjectNOText,'examName': examNameText, 'examNo': examNOText ,'examDateStr':examDateText },
            contentType: 'application/json',
            success: function (result) {
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
                        data:['不及格人数', '及格人数', '中等人数', '优秀人数']
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '66%',
                            center: ['50%', '60%'],
                            data: result,
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
            },
            error: function (err) {
                console.log(err);
            }
         });

}

// 开始查询 
function to_query_exam(server_url, current_page){
    build_examScore_table(server_url,current_page);
    loadEchart(server_url);
}
//保存考试成绩
function save_examScore(server_url){
    let data = {}
    let studentNo = $('#studentNo').val();
    let studentName = $('#studentName').val();
    let examDate = $('#examDate').val();
    let examScoreId = $('#examScoreId').val();
    let subjectNameText = $('#subjectName').find("option:selected").text();
    let subjectNOText = $('#subjectName').val();
    let examNameText = $('#examName').find("option:selected").text();
    let examNOText = $('#examName').val();
    let classNameText = $('#classes').find("option:selected").text();
    let classNOText = $('#classes').val();
    
    
    data['studentNo'] = studentNo;
    data['studentName'] = studentName;
    data['subjectNo'] = subjectNOText;
    data['subjectName'] = subjectNameText;
    data['examNo'] = examNOText;
    data['examName'] = examNameText;


    data['examScore'] = examScoreId;
    data['examDate'] = examDate;
    data['classesNo'] = classNOText;
    data['classesName'] = classNameText;
    console.log(data);

    $.ajax({
        url: server_url,
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.bootstrapGrowl('保存成功', { type: 'success' });
                $('#edit-examScore-modal').modal('hide');
                build_examScore_table(server_url, 1);
            }
        },
        error: function () {
            $.bootstrapGrowl('保存失败', { type: 'danger' });
        }
    });
}
//打开新增成绩页面
function add_examScore(server_url, current_page){
    $('#examDate').datepicker({ language: 'zh-CN' });
    $('#edit-examScore-modal').modal({ 'show': true });
     // clear_examScore();
   build_classes_select('classes',server_url, 1);
   //绑定科目select
   build_subject_select('subjectName',server_url, 1);
   //绑定考试slect
   build_exam_select('examName',server_url, 1);
}
//绑定班级编号select
function build_classes_select(flag, server_url, current_page) {
    $('#'+flag+'').html('');
    debugger;
    $.ajax({
        url: server_url + '/classes/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 120, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let classes_option = $('<option></option>');
                    classes_option.text(obj['name']);
                    classes_option.val(obj['no']);
                    $('#'+flag+'').append(classes_option);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });

}
//绑定课程编号select
function build_subject_select(flag,server_url, current_page){
    $('#'+flag+'').html('');
    $.ajax({
        url: server_url + '/subject/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 120, 'currentPage': current_page },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let subject_option = $('<option></option>');
                    subject_option.text(obj['name']);
                    subject_option.val(obj['no']);
                    $('#'+flag+'').append(subject_option);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}
//绑定考试名称select
function build_exam_select(flag,server_url, current_page){
    $('#'+flag+'').html('');
    $.ajax({
        url: server_url + '/exam/query',
        type: 'get',
        contentType: 'application/json',
        data: { 'pageSize': 120, 'currentPage': current_page },
        success: function (result) {
            var code = result['code'];
            var data = result['data'];
            if (code === '00') {
                $.each(data, function (index, obj) {
                    let exam_option = $('<option></option>');
                    exam_option.text(obj['name']);
                    exam_option.val(obj['no']);
                    $('#'+flag+'').append(exam_option);
                });
            }
        },
        error: function (err) {
            console.log(err);
        }
    });

}
//绑定成绩列表
function build_examScore_table(server_url, current_page) {
    $('#table-content').html('');

    let classNameText = $('#classesId').find("option:selected").text();
    let subjectNameText = $('#subjectNameId').find("option:selected").text();
    let examNameText = $('#examNameId').find("option:selected").text();

    let classNOText = $('#classesId').val();
    let subjectNOText = $('#subjectNameId').val();
    let examNOText = $('#examNameId').val();

    let examDateText=$('#examDateId').val();
   
    debugger;
    // examDateStr 
    $.ajax({
        url: server_url + '/exam/score/query',
        type: 'get',
        contentType: 'application/json',
        data: {'className': classNameText,'classesNo': classNOText, 'subjectName': subjectNameText,'subjectNo': subjectNOText,'examName': examNameText, 'examNo': examNOText ,'examDateStr':examDateText, 'pageSize': 10, 'currentPage': current_page },
        success: function (result) {
            let code = result['code'];
            let data = result['data'];
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
                build_examScore_table_pagination(current_page, result['totalPage'], server_url);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}
//成绩分页查询
function build_examScore_table_pagination(current_page, total_page, server_url) {
    debugger;
    $('#examScore-pagination').html('');
    let page_interval = 2;
    let start_index = current_page - page_interval;
    if (start_index < 1) {
        start_index = 1;
        page_interval = page_interval + Math.abs(current_page - 1 - page_interval);
    }
    let end_index = current_page + page_interval > total_page ? total_page : current_page + page_interval;
    for (let i = start_index; i <= end_index; i++) {
        let page_link = $('<a class=\'page-link\'></a>');
        page_link.text(i);
        page_link.on('click', function () { build_examScore_table(server_url, i); });
        let page_li = $('<li class=\'page-item\'></li>');
        page_li.append(page_link);
        $('#examScore-pagination').append(page_li);
    }
    let previous_link = $('<a class=\'page-link\' aria-label=\'Previous\'><span aria-hidden=\'true\'>&laquo;</span></a>');
    previous_link.on('click', function () { build_examScore_table(server_url, current_page - 1 < 1 ? 1 : current_page - 1) });
    let previous_li = $('<li class=\'page-item\'></li>');
    previous_li.append(previous_link);
    $('#examScore-pagination').prepend(previous_li);
    let next_link = $('<a class=\'page-link\' aria-label=\'Next\'><span aria-hidden=\'true\'>&raquo;</span></a>');
    next_link.on('click', function () { build_examScore_table(server_url, current_page + 1 > total_page ? total_page : current_page + 1) });
    let next_li = $('<li class=\'page-item\'></li>');
    next_li.append(next_link);
    $('#examScore-pagination').append(next_li);
}