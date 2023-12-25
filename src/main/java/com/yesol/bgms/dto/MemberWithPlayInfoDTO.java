package com.yesol.bgms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberWithPlayInfoDTO {

    private String memberCode;
    private String memberName;
    private String gradeCode;
    private Date regDate;
    private String sex;
    private String adminYn;
    private Integer playCount;

    public MemberWithPlayInfoDTO() {
    }

    public MemberWithPlayInfoDTO(String memberCode, String memberName, String gradeCode, Date regDate, String sex, String adminYn, Integer playCount) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.gradeCode = gradeCode;
        this.regDate = regDate;
        this.sex = sex;
        this.adminYn = adminYn;
        this.playCount = playCount;
    }

}
