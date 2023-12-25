package com.yesol.bgms.repository;

import com.yesol.bgms.dto.MemberWithPlayInfoDTO;
import com.yesol.bgms.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT NEW com.yesol.bgms.dto.MemberWithPlayInfoDTO(m.memberCode, m.memberName, m.gradeCode, m.regDate, m.sex, m.adminYn, g.playCount) " +
            "FROM Member m LEFT JOIN Game g ON m.memberCode = g.memberCode")
    List<MemberWithPlayInfoDTO> selectAllMember();

    Member findByMemberCode(String memberCode);
}
