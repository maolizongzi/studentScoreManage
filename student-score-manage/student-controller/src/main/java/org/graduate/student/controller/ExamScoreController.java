package org.graduate.student.controller;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.controller.utility.ExamScoreUtil;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.graduate.student.service.ExamScoreService;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exam/score")
public class ExamScoreController {

    private final ExamScoreService examScoreService;

    public ExamScoreController(ExamScoreService examScoreService) {
        this.examScoreService = examScoreService;
    }

    /**
     * 新增成绩
     * @param examScoreEntity
     * @return
     */
    @PostMapping("add")
    public ExamScoreEntity addExamScoreEntity(ExamScoreEntity examScoreEntity) {
        ExamScore examScore = ExamScoreUtil.toExamScore(examScoreEntity);
        examScoreService.addExamScore(examScore);
        return ExamScoreUtil.toExamScoreEntity(examScore);
    }
    /**
     * 根据条件查询，班级/考试/科目
     */
    @GetMapping("query")
    public QueryResultEntity<List<ExamScoreEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage) {
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam();
        examScoreQueryParam.setPageSize(pageSize);
        examScoreQueryParam.setCurrentPage(currentPage);
        return examScoreService.query(examScoreQueryParam);
    }
    /**
     * 生成班级饼图
     * 不及格人数<60/及格人数60<x<74/中等人数74<x<84/优秀人数84<x<=100
     */
    @GetMapping("pieChart")
    public QueryResultEntity<List<ExamScoreEntity>> pieChart(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage) {
        ExamScoreQueryParam examScoreQueryParam = new ExamScoreQueryParam();
        examScoreQueryParam.setPageSize(pageSize);
        examScoreQueryParam.setCurrentPage(currentPage);
        return examScoreService.query(examScoreQueryParam);
    }

    /**
     * 生成年级折线图
     * x轴 不及格/及格/中等/优秀...
     * Y轴：人数
     * 一个班级对应一条折线
     */

    /**
     * 成绩下载，查询按钮旁边有个下载功能
     */


}
