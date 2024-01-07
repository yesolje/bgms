package com.yesol.bgms.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(GameProgressId.class)
@Table(name="GAME_PROGRESS")
public class GameProgress {

    @Id
    @Column(name = "COURT_ID")
    private String courtId;

    @Id
    @Column(name = "MEMBER_CODE")
    private String memberCode;

    @Column(name = "TEAM")
    private String team;

    @Column(name = "PROGRESS")
    private int progress;
}
