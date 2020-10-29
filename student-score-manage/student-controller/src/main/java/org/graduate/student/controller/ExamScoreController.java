package org.graduate.student.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.controller.utility.ExamScoreUtil;
import org.graduate.student.repository.model.ClassesLineChart;
import org.graduate.student.repository.model.ClassesPieChart;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.graduate.student.service.ExamScoreService;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.graduate.student.service.entity.StudentEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("exam/score")
public class ExamScoreController {

    private final ExamScoreService examScoreService;

    public ExamScoreController(ExamScoreService examScoreService) {
        this.examScoreService = examScoreService;
    }

    /**
     * 新增成绩
     *
     * @return
     */
    @PostMapping("add")
    public BaseResultEntity<ExamScoreEntity> add(@RequestBody ExamScoreEntity examScoreEntity){
        BaseResultEntity<ExamScoreEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            //examScoreEntity.setExamDate(new SimpleDateFormat("yyyy-MM-dd").parse(examScoreEntity.getExamDateStr()));
            ExamScoreEntity resultStudentEntity =examScoreService.addExamScore(examScoreEntity);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
            baseResultEntity.setData(resultStudentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }

    /**
     * 根据条件查询，班级/考试/科目
        @RequestParam(value = "className", required = false) String className,
                  @RequestParam(value = "classesNo", required = false) String classesNo,
                  @RequestParam(value = "subjectName", required = false) String subjectName,
                 @RequestParam(value = "subjectNo", required = false) String subjectNo,
                  @RequestParam(value = "examName", required = false) String examName,
                  @RequestParam(value = "examNo", required = false) String examNo,
                  @RequestParam("pageSize") Integer pageSize,
                  @RequestParam("currentPage") Integer currentPage
     */
    @GetMapping("query")
    public QueryResultEntity<List<ExamScoreEntity>> query(
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "classesNo", required = false) String classesNo,
            @RequestParam(value = "subjectName", required = false) String subjectName,
            @RequestParam(value = "subjectNo", required = false) String subjectNo,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "examNo", required = false) String examNo,
            @RequestParam(value = "examDateStr", required = false) String examDateStr,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage) {
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam(currentPage,pageSize);
        examScoreQueryParam.setClassesNo(classesNo);
        examScoreQueryParam.setClassesName(className);
        examScoreQueryParam.setSubjectNo(subjectNo);
        examScoreQueryParam.setSubjectName(subjectName);
        examScoreQueryParam.setExamNo(examNo);
        examScoreQueryParam.setExamName(examName);
        examScoreQueryParam.setExamDateStr(examDateStr);
        return examScoreService.query(examScoreQueryParam);
    }

    /**
     * 生成班级饼图 班级编号(2019001),考试科目，考试时间，考试名称(随堂考/月考/期中考/期末考)进行查询
     * 不及格人数<60/及格人数60<x<74/中等人数74<x<84/优秀人数84<x<=100
     */
    @GetMapping("pieChart")
    @ResponseBody
    public ArrayList<ClassesPieChart> pieChart(
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "classesNo", required = false) String classesNo,
            @RequestParam(value = "subjectName", required = false) String subjectName,
            @RequestParam(value = "subjectNo", required = false) String subjectNo,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "examNo", required = false) String examNo,
            @RequestParam(value = "examDateStr", required = false) String examDateStr
    ) {
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam();
        examScoreQueryParam.setClassesNo(classesNo);
        examScoreQueryParam.setExamName(examName);
        examScoreQueryParam.setSubjectName(subjectName);
        examScoreQueryParam.setExamDateStr(examDateStr);
        ExamScoreQueryParam examScore = examScoreService.countByPhaseScore(examScoreQueryParam);
        //返回json串
        ArrayList<ClassesPieChart> productArrayList = new ArrayList<ClassesPieChart>();
        ClassesPieChart classesPieChart1 = new ClassesPieChart();
        classesPieChart1.setName("不及格人数");
        classesPieChart1.setValue(examScore.getFlunkPeople());
        ClassesPieChart classesPieChart2 = new ClassesPieChart();
        classesPieChart2.setName("及格人数");
        classesPieChart2.setValue(examScore.getPassPeople());
        ClassesPieChart classesPieChart3 = new ClassesPieChart();
        classesPieChart3.setName("中等人数");
        classesPieChart3.setValue(examScore.getAveragePeople());
        ClassesPieChart classesPieChart4 = new ClassesPieChart();
        classesPieChart4.setName("优秀人数");
        classesPieChart4.setValue(examScore.getExcellentPeople());
        productArrayList.add(classesPieChart1);
        productArrayList.add(classesPieChart2);
        productArrayList.add(classesPieChart3);
        productArrayList.add(classesPieChart4);
        return productArrayList;
    }

    /**
     * 生成年级折线图
     * x轴 不及格/及格/中等/优秀...
     * Y轴：人数
     * 一个班级对应一条折线
     */
    @GetMapping("lineChart")
    @ResponseBody
    public ArrayList<ClassesLineChart> lineChart() {
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam();
        examScoreQueryParam.setClassesName("2019");
        examScoreQueryParam.setExamName("期中");
        examScoreQueryParam.setSubjectName("数学");
        examScoreQueryParam.setExamDateStr("2020-10-14");
        List<ExamScoreQueryParam> examScoreList = examScoreService.countByGradeScore(examScoreQueryParam);
        ArrayList<ClassesLineChart> productArrayList = new ArrayList<ClassesLineChart>();
        for (ExamScoreQueryParam exam:examScoreList){
            ClassesLineChart  cl=new ClassesLineChart();
            cl.setName(exam.getClassesName());
            Integer[] array=new Integer[4];
            array[0]=exam.getFlunkPeople();
            array[1]=exam.getPassPeople();
            array[2]=exam.getAveragePeople();
            array[3]=exam.getExcellentPeople();
            cl.setData(array);
            cl.setType("line");
            productArrayList.add(cl);
        }
        return productArrayList;
    }

    /**
     * 成绩下载
     */
    @GetMapping("downExamScore")
    public QueryResultEntity<List<ExamScoreEntity>> downExamScore(HttpServletResponse response) {
        ExamScoreUtil util=new ExamScoreUtil();
        String   fileName="/Users/lina/78120201001BSP";
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam();
        examScoreQueryParam.setClassesNo("2019001");
        examScoreQueryParam.setExamName("期中");
        examScoreQueryParam.setExamDateStr("2020-10-14");
        List<ExamScoreQueryParam> examScoreList=examScoreService.queryStudentAllScore(examScoreQueryParam);
        String[] str1 = { "studentNo", "studentName", "chinese","mathematical","english","physical","chemical","biological","geography","geography","history","allScore"};
        String str2 = "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\" \n";
        try {
            String  res = util.list2String(examScoreList, str2, str1);
            String filePath = fileName+".csv";
            util.contentToTxt(filePath, res, true);
            util.downLoad3(filePath, response);
        }catch (Exception e){
            e.printStackTrace();
        }
        ExamScoreQueryParam ex = new ExamScoreQueryParam();
        return examScoreService.query(ex);
    }

}
