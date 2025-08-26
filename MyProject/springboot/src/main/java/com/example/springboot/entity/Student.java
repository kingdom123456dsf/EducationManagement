package com.example.springboot.entity;

import javax.persistence.*;

@Table(name = "student")
public class Student {
    @Id
    private String Sno;

    @Column(name = "Sname")
    private String Sname;

    @Column(name = "Ssex")
    private char Ssex;

    @Column(name = "Sage")
    private Integer Sage;

    @Column(name = "SLocation")
    private String SLocation;

    @Column(name = "SNation")
    private String SNation;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public char getSsex() {
        return Ssex;
    }

    public void setSsex(char ssex) {
        Ssex = ssex;
    }

    public Integer getSage() {
        return Sage;
    }

    public void setSage(Integer sage) {
        Sage = sage;
    }

    public String getSLocation() {
        return SLocation;
    }

    public void setSLocation(String SLocation) {
        this.SLocation = SLocation;
    }

    public String getSNation() {
        return SNation;
    }

    public void setSNation(String SNation) {
        this.SNation = SNation;
    }
}
