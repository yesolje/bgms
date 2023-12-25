package com.yesol.bgms.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_CODE")
    private String memberCode;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "GRADE_CODE")
    private String gradeCode;

    @Column(name = "REG_DATE")
    private Date regDate;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "ADMIN_YN")
    private String adminYn;



}
