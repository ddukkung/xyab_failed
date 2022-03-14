package com.miree.xyab.controller;

import com.miree.xyab.domain.Board;
import com.miree.xyab.dto.UserDto;
import com.miree.xyab.dto.request.BoardRequestDto;
import com.miree.xyab.dto.response.BoardResponseDto;
import com.miree.xyab.repository.BoardRepository;
import com.miree.xyab.service.BoardService;
import com.miree.xyab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RepositoryRestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @PostMapping(value = "/boards", produces = "application/json; charset=utf-8")
    public ResponseEntity<BoardResponseDto> saveBoard(@RequestBody BoardRequestDto boardDto, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        return ResponseEntity.ok(boardService.save(boardDto, userService.toEntity(userDto)));
    }

    @PutMapping(value = "/boards/{idx}", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> updateBoard(@PathVariable Long idx, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(idx, boardRequestDto) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long idx) {
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
