package com.miree.xyab.repository;

import com.miree.xyab.domain.Board;
import com.miree.xyab.domain.User;
import com.miree.xyab.domain.enums.BoardType;
import com.miree.xyab.domain.projection.BoardOnlyContainTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = BoardOnlyContainTitle.class)
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);

    @RestResource(path = "query")
    List<Board> findByTitle(@Param("title") String title);

    @Modifying
    @Query("update Board b set b.title = :title, b.content = :content, b.boardType = :boardType, b.updatedDate = current_timestamp where b.idx = :id")
    int updateBoardById(@Param("id") Long id, @Param("title") String title, @Param("content") String content, @Param("boardType") BoardType boardType);
}
