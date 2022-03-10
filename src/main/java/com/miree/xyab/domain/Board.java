package com.miree.xyab.domain;

import com.miree.xyab.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private Long viewCnt;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(String title, String content, BoardType boardType, User user) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
        this.viewCnt = 0L;
        this.setCreatedDate();
        this.setUpdatedDate();
    }

    public void update(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardType = board.getBoardType();
        this.setUpdatedDate();
    }

    public Board setViewCtn() {
        this.viewCnt++;
        return this;
    }

}
