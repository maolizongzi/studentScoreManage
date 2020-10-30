package org.graduate.student.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

import java.util.Date;

public class ExamScoreQueryParam extends BaseQueryParam {
    public ExamScoreQueryParam(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

    public ExamScoreQueryParam() {
    }

    //生成班级饼图 班级编号(2019001) ,考试科目，考试时间，考试名称(随堂考/月考/期中考/期末考)进行查询
    private Long id;
    private Long studentId;
    private String studentNo;
    private String studentName;
    private Long subjectId;
    private String subjectNo;//考试科目 (1001001 语文)/(1001002 数学)/(1001003 英语)
    private String subjectName;
    private Long examId;
    private String examNo;//考试名称 (1001 随堂考)/(1002 月考)/(1003 期中)/(1004 期末)
    private String examName;
    private Double examScore;
    private Date examDate;//考试时间
    private Long classesId;
    private String classesNo;//classNo 2019001/2019002/2019003/2019004
    private String classesName;



    private Integer flunkPeople;
    private Integer passPeople;
    private Integer averagePeople;
    private Integer excellentPeople;
    private String examDateStr;
    private String chinese;//语文
    private String mathematical; //数学
    private String english;  //英语
    private String physical; //物理
    private String chemical; //化学
    private String biological; //生物
    private String political; //政治
    private String geography; //地理
    private String history; //历史
    private String allScore;//总成绩

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Long getExamId() {
        return examId;
    }

    public String getExamNo() {
        return examNo;
    }

    public String getExamName() {
        return examName;
    }

    public Double getExamScore() {
        return examScore;
    }

    public Date getExamDate() {
        return examDate;
    }

    public Long getClassesId() {
        return classesId;
    }

    public String getClassesNo() {
        return classesNo;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    public void setClassesNo(String classesNo) {
        this.classesNo = classesNo;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Integer getFlunkPeople() {
        return flunkPeople;
    }

    public void setFlunkPeople(Integer flunkPeople) {
        this.flunkPeople = flunkPeople;
    }

    public Integer getPassPeople() {
        return passPeople;
    }

    public void setPassPeople(Integer passPeople) {
        this.passPeople = passPeople;
    }

    public Integer getAveragePeople() {
        return averagePeople;
    }

    public void setAveragePeople(Integer averagePeople) {
        this.averagePeople = averagePeople;
    }

    public Integer getExcellentPeople() {
        return excellentPeople;
    }

    public void setExcellentPeople(Integer excellentPeople) {
        this.excellentPeople = excellentPeople;
    }

    public String getExamDateStr() {
        return examDateStr;
    }

    public void setExamDateStr(String examDateStr) {
        this.examDateStr = examDateStr;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMathematical() {
        return mathematical;
    }

    public void setMathematical(String mathematical) {
        this.mathematical = mathematical;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    public String getChemical() {
        return chemical;
    }

    public void setChemical(String chemical) {
        this.chemical = chemical;
    }

    public String getBiological() {
        return biological;
    }

    public void setBiological(String biological) {
        this.biological = biological;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getAllScore() {
        return allScore;
    }

    public void setAllScore(String allScore) {
        this.allScore = allScore;
    }
}
