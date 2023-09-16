package seb45.test.pettalk.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seb45.test.pettalk.common.entity.Board;
import seb45.test.pettalk.common.vo.ResponseVo;

@RestController
@RequestMapping(value = "/api/time-test")
public class TimeController {

    @GetMapping(value = "/")
    public ResponseEntity<ResponseVo> findTimeListByPetSitter(@RequestParam Long petSitterId) throws Exception {

        ResponseVo responseVo = new ResponseVo("00", "성공입니다.");
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) throws Exception {

        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
