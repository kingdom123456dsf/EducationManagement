package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "teacher")
public class Teacher {
    @Id
    private String Tno;

    @Column(name = "Tname")
    private String Tname;

    @Column(name = "Tsex")
    private char Tsex;

    @Column(name = "Tage")
    private Integer Tage;

    @Column(name = "TLocation")
    private String TLocation;

    @Column(name = "TNation")
    private String TNation;

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public char getTsex() {
        return Tsex;
    }

    public void setTsex(char tsex) {
        Tsex = tsex;
    }

    public Integer getTage() {
        return Tage;
    }

    public void setTage(Integer tage) {
        Tage = tage;
    }

    public String getTLocation() {
        return TLocation;
    }

    public void setTLocation(String TLocation) {
        this.TLocation = TLocation;
    }

    public String getTNation() {
        return TNation;
    }

    public void setTNation(String TNation) {
        this.TNation = TNation;
    }
}
