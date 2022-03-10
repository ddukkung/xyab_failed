package com.miree.xyab.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
    // 게시물 등록, 수정, 상세 조회에 필요한 필드 정의
    private String title;
    private String content;
    private String boardType;
}
