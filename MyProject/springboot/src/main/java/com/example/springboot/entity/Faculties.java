package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "faculties")
public class Faculties {
    @Id
    private String Dno;

    @Column(name = "Dname")
    private String Dname;

    @Column(name = "DLocation")
    private String DLocation;

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDLocation() {
        return DLocation;
    }

    public void setDLocation(String DLocation) {
        this.DLocation = DLocation;
    }
}
