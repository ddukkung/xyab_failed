package com.miree.xyab.event;

import com.miree.xyab.domain.Board;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler
public class BoardEventHandler {

    // 게시글 생성 날짜를 현재 시간으로 할당
    @HandleBeforeCreate
    public void beforeCreateBoard(Board board) {
        board.setCreatedDateNow();
    }

    // 게시글 수정 시 수정 날짜를 현재 시간으로 할당
    @HandleBeforeSave
    public void beforeSaveBoard(Board board) {
        board.setUpdatedDateNow();
    }
}
