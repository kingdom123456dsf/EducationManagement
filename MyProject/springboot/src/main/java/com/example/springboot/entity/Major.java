package com.example.springboot.entity;

import javax.persistence.*;

@Table(name = "major")
public class Major {
    @Id
    private String Mno;

    @Column(name = "Mname")
    private String Mname;

    @Column(name = "Dno")
    private String Dno;

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }
}
