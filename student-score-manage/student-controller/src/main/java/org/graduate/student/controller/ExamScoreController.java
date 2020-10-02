package org.graduate.student.controller;

import org.graduate.student.controller.utility.ExamScoreUtil;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.service.ExamScoreService;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exam/score")
public class ExamScoreController {

    private final ExamScoreService examScoreService;

    public ExamScoreController(ExamScoreService examScoreService) {
        this.examScoreService = examScoreService;
    }

    @PostMapping("add")
    public ExamScoreEntity addExamScoreEntity(ExamScoreEntity examScoreEntity) {
        ExamScore examScore = ExamScoreUtil.toExamScore(examScoreEntity);
        examScoreService.addExamScore(examScore);
        return ExamScoreUtil.toExamScoreEntity(examScore);


    }
}
