package seb45.test.pettalk.common.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seb45.test.pettalk.common.vo.RequestVo;
import seb45.test.pettalk.common.vo.ResponseVo;

@RestController
@RequestMapping(value = "/api/aop-test")
public class SampleController {

    @GetMapping(value = "/")
    public ResponseEntity<ResponseVo> sampleGetApi(@RequestParam String code) throws Exception {

        if (code.equals("1"))
            throw new Exception("실패입니다.");
        ResponseVo responseVo = new ResponseVo("00", "성공입니다.");
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }

    @PostMapping(value = "/samplePostApi")
    public ResponseEntity<ResponseVo> samplePostApi(@RequestBody RequestVo input) {

        ResponseVo responseVo = new ResponseVo();
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
}
