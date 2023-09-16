package seb45.test.pettalk.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import seb45.test.pettalk.common.dto.TimeDto;
import seb45.test.pettalk.common.entity.Application;
import seb45.test.pettalk.common.entity.Board;
import seb45.test.pettalk.common.repository.ApplicationRepository;
import seb45.test.pettalk.common.repository.BoardRepository;
import seb45.test.pettalk.common.service.TimeService;
import seb45.test.pettalk.common.vo.ResponseVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/time-test")
@RequiredArgsConstructor
@Transactional
public class TimeController {

    private final ApplicationRepository applicationRepository;
    private final BoardRepository boardRepository;
    private final TimeService timeService;


    @GetMapping(value = "/")
    public ResponseEntity<List<TimeDto>> findTimeListByPetSitter(@RequestParam Long petSitterId) {
        return new ResponseEntity<>(timeService.getTimeListByPetSitterId(petSitterId), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseVo> createBoard(@RequestBody Board board) throws Exception {

        List<TimeDto> timeDtoList = timeService.getTimeListByPetSitterId(board.getPetSitterId());
        if (this.checkDupTime(board, timeDtoList)) {
            return new ResponseEntity<>(new ResponseVo("99", "펫시터가 이미 지원한 시간대와 겹칩니다."), HttpStatus.BAD_REQUEST);
        } else {
            long petSitterId = board.getPetSitterId();
            board.setPetSitterId(null);
            board.setCreatedAt(LocalDateTime.now());
            boardRepository.save(board);
            applicationRepository.save(new Application(petSitterId,board, petSitterId));
        }
        return new ResponseEntity<>(new ResponseVo("00", "성공입니다."), HttpStatus.CREATED);
    }


    // 사실 util 폴더로 따로 분리해서, 모든 서비스에서 시간관련 체크할 때 사용하게 해도 되는 유틸성 메소드들
    // 분리된다면 static public
    private boolean checkDupTime(Board board, List<TimeDto> timeDtoList) {
        LocalDateTime boardStartTime = board.getStartTime();
        LocalDateTime boardEndTime = board.getEndTime();

        return timeDtoList.stream()
                .anyMatch(timeDto -> isOverlap(boardStartTime, boardEndTime, timeDto.getStartTime(), timeDto.getEndTime()));
    }

    private boolean isOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
