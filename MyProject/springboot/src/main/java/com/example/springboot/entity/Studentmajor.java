package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "studentmajor")
public class Studentmajor {
    @Column(name = "Sno")
    private String Sno;

    @Column(name = "Mno")
    private String Mno;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }
}
