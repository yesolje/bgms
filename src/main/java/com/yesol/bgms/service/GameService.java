package com.yesol.bgms.service;

import com.yesol.bgms.dto.MemberWithPlayAndGradeInfoDTO;
import com.yesol.bgms.dto.MemberWithPlayInfoDTO;
import com.yesol.bgms.repository.GameRepository;
import com.yesol.bgms.repository.MemberRepository;
import com.yesol.bgms.vo.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameRepository.class);

    public List getAllPlayer() {
        List<MemberWithPlayAndGradeInfoDTO> list = new ArrayList();
        try {
            list = gameRepository.selectAllPlayer();
        } catch(Exception e){
            logger.error("GameService.getAllPlayer ERROR :{}",e);
        }
        return list;
    }
    public int deleteGamePlayer(String memberCode){
        int result = 0;
        try {
            result = gameRepository.deleteGamePlayer(memberCode);
        } catch(Exception e){
            logger.error("GameService.deleteGamePlayer ERROR :{}",e);
        }
        return result;
    }
    public int insertGamePlayer(Member m){
        int result = 0;
        try {
            //INSERT INTO table_1 ( column_1, column_2 ) VALUES ( 'b', 'bb' );
            result = gameRepository.insertGamePlayer(m.getMemberCode(),m.getMemberName(),0);
        } catch(Exception e){
            logger.error("GameService.insertGamePlayer ERROR :{}",e);
        }
        return result;
    }
}
