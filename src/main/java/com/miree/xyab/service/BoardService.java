package com.miree.xyab.service;

import com.miree.xyab.domain.Board;
import com.miree.xyab.domain.User;
import com.miree.xyab.domain.enums.BoardType;
import com.miree.xyab.dto.request.BoardRequestDto;
import com.miree.xyab.dto.response.BoardResponseDto;
import com.miree.xyab.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return boardRepository.findAll(pageable);

    }

    public BoardResponseDto findBoardByIdx(Long idx) {
        Optional<Board> optBoard = boardRepository.findById(idx);
        Board board = new Board();

        if (optBoard.isPresent()) {
            board = optBoard.get();
        }

        return new BoardResponseDto(board.updateViewCnt());
    }


    public BoardResponseDto save(BoardRequestDto boardRequestDto, User user) {
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .boardType(BoardType.valueOf(boardRequestDto.getBoardType()))
                .user(user)
                .build();
        return new BoardResponseDto(boardRepository.save(board));

    }

    public boolean update(Long idx, BoardRequestDto boardRequestDto) {
        return boardRepository.updateBoardById(idx, boardRequestDto.getTitle(), boardRequestDto.getContent(), BoardType.valueOf(boardRequestDto.getBoardType())) > 0;
    }
}
