package com.yesol.bgms.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="GRADE")
public class Grade {
    @Id
    @Column(name = "GRADE_CODE")
    private String gradeCode;

    @Column(name = "GRADE_NAME")
    private String gradeName;

    @Column(name = "GRADE_SCORE")
    private int gradeScore;

}
