package com.yesol.bgms.service;

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
public class MemberService {

    private final MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameRepository.class);

    public List getAllMember() {
        
        List<MemberWithPlayInfoDTO> memberWithPlayList = new ArrayList();
        try {
            memberWithPlayList = memberRepository.selectAllMember();

        } catch(Exception e){
            logger.error("MemberService.getAllMember ERROR :{}",e);
        }
        return memberWithPlayList;
    }

    public Member findByMemberCode(String memberCode){
        Member m = new Member();
        try {
            m = memberRepository.findByMemberCode(memberCode);

        } catch(Exception e){
            logger.error("MemberService.findByMemberCode ERROR :{}",e);
        }
        return m;
    }
}
