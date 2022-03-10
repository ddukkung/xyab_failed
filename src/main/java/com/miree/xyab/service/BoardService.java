package com.miree.xyab.service;

import com.miree.xyab.domain.Board;
import com.miree.xyab.dto.response.BoardResponseDto;
import com.miree.xyab.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    public BoardResponseDto findBoardByIdx(Long idx) {
        Board board = boardRepository.findById(idx).get();
        return new BoardResponseDto(board.updateViewCnt());
    }


}
