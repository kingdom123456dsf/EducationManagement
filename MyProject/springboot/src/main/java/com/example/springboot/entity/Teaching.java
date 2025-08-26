package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "teaching")
public class Teaching {
    @Column(name = "Tno")
    private String Tno;

    @Column(name = "Cno")
    private String Cno;

    @Column(name = "TCLocation")
    private String TCLocation;

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getTCLocation() {
        return TCLocation;
    }

    public void setTCLocation(String TCLocation) {
        this.TCLocation = TCLocation;
    }
}
