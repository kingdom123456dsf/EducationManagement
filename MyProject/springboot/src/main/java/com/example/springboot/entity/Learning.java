package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "learning")
public class Learning {
    @Id
    private String Sno;

    @Column(name = "Cno")
    private String Cno;

    @Column(name = "Score")
    private char Score;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public char getScore() {
        return Score;
    }

    public void setScore(char score) {
        Score = score;
    }
}
