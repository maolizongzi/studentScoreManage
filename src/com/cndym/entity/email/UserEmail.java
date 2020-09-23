package com.cndym.entity.email;

import java.util.Date;

public class UserEmail {

    private Long id;
    //??????email
    private String receiveEmail;
    //??????code
    private String receiveCode;
    //???????
    private String title;
    //???????
    private String body;
    //?????(0 ¦Ä???? 1 ?????? 2 ???????)
    private Integer status;
    //???????
    private Date sendTime;
    //???????
    private Date createTime;

    public UserEmail() {

    }

    public UserEmail(Long id, String receiveEmail, String receiveCode, String title, String body, Integer status, Date createTime, Date sendTime) {
        this.id = id;
        this.receiveEmail = receiveEmail;
        this.receiveCode = receiveCode;
        this.title = title;
        this.body = body;
        this.status = status;
        this.createTime = createTime;
        this.sendTime = sendTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
