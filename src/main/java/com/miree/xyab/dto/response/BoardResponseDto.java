package com.miree.xyab.dto.response;

import com.miree.xyab.domain.Board;
import com.miree.xyab.dto.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    // 게시물 목록, 게시물 상세 조회에 필요한 필드 정의

    private final Long idx;
    private final String title;
    private final String content;
    private final String boardType;
    private final Long viewCnt;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    private final UserDto user;

    public BoardResponseDto(Board board) {
        this.idx = board.getIdx();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardType = board.getBoardType().getValue();
        this.viewCnt = board.getViewCnt();
        this.createdDate = board.getCreatedDate();
        this.updatedDate = board.getUpdatedDate();

        if (board.getUser() != null) {
            this.user = new UserDto(board.getUser());
        } else {
            this.user = null;
        }
    }
}
