package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "teacherfaculties")
public class Teacherfaculties {
    @Column(name = "Tno")
    private String Tno;

    @Column(name = "Dno")
    private String Dno;

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }
}
