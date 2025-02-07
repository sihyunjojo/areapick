package com.d108.project.domain.forum.board.repository;

import com.d108.project.domain.forum.board.dto.BoardRequestSearchDto;
import com.d108.project.domain.forum.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 지역 게시판들 조회 (상권 ID가 있는 게시판들)
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.area WHERE b.area IS NOT NULL")
    Page<Board> findByAreaIdIsNotNull(Pageable pageable);

    // 특정 판매 관련 게시판 조회
    @Query("SELECT b FROM Board b WHERE b.franchise IS NULL and b.area IS NULL") // 판매 게시판 조회 로직은 수정 필요
    Optional<Board> findSaleBoard();

    // 프랜차이즈 게시판들 조회 (프랜차이즈 ID가 있는 게시판)
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.franchise WHERE b.franchise IS NOT NULL")/// 패치조인
    Page<Board> findByFranchiseIdIsNotNull(Pageable pageable);

    // 프랜차이즈 게시판 검색 (페치 조인으로 N+1 문제 해결)
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.franchise WHERE b.franchise IS NOT NULL AND b.franchise.name LIKE %:keyword%")
    Page<Board> searchFranchiseBoardsByKeyword(String keyword, Pageable pageable);

}
