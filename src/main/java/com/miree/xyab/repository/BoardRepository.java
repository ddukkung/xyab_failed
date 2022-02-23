package com.miree.xyab.repository;

import com.miree.xyab.domain.Board;
import com.miree.xyab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
