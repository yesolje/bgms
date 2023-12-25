package com.yesol.bgms.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="GAME")
public class Game {

    @Id
    @Column(name = "MEMBER_CODE")
    private String memberCode;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "PLAY_COUNT")
    private int playCount;

}
