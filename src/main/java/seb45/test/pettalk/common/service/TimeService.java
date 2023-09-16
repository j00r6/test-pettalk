package seb45.test.pettalk.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seb45.test.pettalk.common.dto.TimeDto;
import seb45.test.pettalk.common.entity.Application;
import seb45.test.pettalk.common.entity.Board;
import seb45.test.pettalk.common.repository.ApplicationRepository;
import seb45.test.pettalk.common.repository.BoardRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final ApplicationRepository applicationRepository;
    private final BoardRepository boardRepository;

    public List<TimeDto> getTimeListByPetSitterId(Long petSitterId) {
        List<Application> applications = applicationRepository.findByPetSitterId(petSitterId);
        return applications.stream()
                .map(application -> {
                    Board board = boardRepository.findById(application.getBoard().getId()).orElse(null);
                    if (board != null) {
                        return new TimeDto(board.getStartTime(), board.getEndTime());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
