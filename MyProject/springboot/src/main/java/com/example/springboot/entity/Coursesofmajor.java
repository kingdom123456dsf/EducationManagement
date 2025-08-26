package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "coursesofmajor")
public class Coursesofmajor {
    @Column(name = "Cno")
    private String Cno;

    @Column(name = "Mno")
    private String Mno;

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }
}
