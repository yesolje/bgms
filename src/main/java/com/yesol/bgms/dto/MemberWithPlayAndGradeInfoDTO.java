package com.yesol.bgms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberWithPlayAndGradeInfoDTO {

    private String memberCode;
    private String memberName;
    private String gradeCode;
    private Date regDate;
    private String sex;
    private String adminYn;
    private int playCount;

    private int gradeScore;

    private String gradeName;

    public MemberWithPlayAndGradeInfoDTO() {
    }

    public MemberWithPlayAndGradeInfoDTO(String memberCode, String memberName, String gradeCode, Date regDate, String sex, String adminYn, int playCount , int gradeScore, String gradeName) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.gradeCode = gradeCode;
        this.regDate = regDate;
        this.sex = sex;
        this.adminYn = adminYn;
        this.playCount = playCount;
        this.gradeScore = gradeScore;
        this.gradeName = gradeName;

    }

}
