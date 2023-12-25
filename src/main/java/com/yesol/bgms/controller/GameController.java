package com.yesol.bgms.controller;

import com.yesol.bgms.service.GameService;
import com.yesol.bgms.service.MemberService;
import com.yesol.bgms.vo.Game;
import com.yesol.bgms.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    private final MemberService memberService;

    //게임매치 화면에서 출전 선수 명단 보기
    @RequestMapping(value = "/viewPlayer", produces ="application/json")
    public ResponseEntity<?> viewPlayer() {
        String strResult = "";
        List<?> playerResult = new ArrayList();
        playerResult = gameService.getAllPlayer();
        ResponseEntity res = new ResponseEntity<>(playerResult, HttpStatus.OK);
        return res;
    }

    //출석체크 화면에서 멤버 전체 보기
    @RequestMapping(value = "/viewMember", produces ="application/json")
    public ResponseEntity<?> viewMember() {
        String strResult = "";
        List<?> playerResult = new ArrayList();
        playerResult = memberService.getAllMember();
        ResponseEntity res = new ResponseEntity<>(playerResult, HttpStatus.OK);
        return res;
    }

    //멤버 출석 상태 변경하기
    @RequestMapping(value = "/changeAttendStatus", produces ="application/json")
    public ResponseEntity<?> changeAttendStatus(@RequestBody Object data) {
        System.out.println(data.toString());
        HashMap<String,String> dataMap = (HashMap)data;
        String memberCode = dataMap.get("member_code");
        String action = dataMap.get("act");
        List<?> playerResult = new ArrayList();
        int resultCount =0;
        if("Attend".equals(action)){

            try{
                Member m = memberService.findByMemberCode(memberCode);
                resultCount = gameService.insertGamePlayer(m);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else if("Cancel".equals(action)){
            try{
                resultCount = gameService.deleteGamePlayer(memberCode);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }


        ResponseEntity res = new ResponseEntity<>(resultCount, HttpStatus.OK);
        return res;
    }

}
