package com.miree.xyab.controller;

import com.miree.xyab.domain.Board;
import com.miree.xyab.dto.UserDto;
import com.miree.xyab.dto.response.BoardResponseDto;
import com.miree.xyab.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

    @GetMapping("/detail")
    public String board(@RequestParam(defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "board/detail";
    }

    @GetMapping("/write")
    public String write(@RequestParam(required = false) Long idx, Model model, UserDto userDto) {
        if (idx != null) {
            BoardResponseDto board = boardService.findBoardByIdx(idx);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

}
