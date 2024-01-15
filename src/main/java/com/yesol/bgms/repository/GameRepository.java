package com.yesol.bgms.repository;

import com.yesol.bgms.dto.MemberWithPlayAndGradeInfoDTO;
import com.yesol.bgms.dto.MemberWithPlayInfoDTO;
import com.yesol.bgms.vo.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {

    @Query("SELECT NEW com.yesol.bgms.dto.MemberWithPlayAndGradeInfoDTO(" +
            "g.memberCode, g.memberName, m.gradeCode, m.regDate, m.sex, m.adminYn, g.playCount, gr.gradeScore, gr.gradeName) " +
            "FROM com.yesol.bgms.vo.Game g " +
            "LEFT JOIN com.yesol.bgms.vo.Member m ON g.memberCode = m.memberCode " +
            "LEFT JOIN com.yesol.bgms.vo.Grade gr ON m.gradeCode = gr.gradeCode")
    List<MemberWithPlayAndGradeInfoDTO> selectAllPlayer();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Game g WHERE g.memberCode = :memberCode")
    int deleteGamePlayer(@Param("memberCode") String memberCode);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO game (member_code, member_name, play_count) VALUES (:memberCode, :memberName, :playCount)", nativeQuery = true)
    int insertGamePlayer(@Param("memberCode") String memberCode, @Param("memberName") String memberName, @Param("playCount") int playCount);
}
