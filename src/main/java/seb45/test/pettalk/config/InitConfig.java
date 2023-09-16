package seb45.test.pettalk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import seb45.test.pettalk.common.entity.Application;
import seb45.test.pettalk.common.entity.Board;
import seb45.test.pettalk.common.repository.ApplicationRepository;
import seb45.test.pettalk.common.repository.BoardRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Configuration
@Slf4j
public class InitConfig {

    @Bean
    public CommandLineRunner initDatabase(BoardRepository boardRepository, ApplicationRepository applicationRepository) {
        return args -> {
            Random random = new Random();

            for (int i = 0; i < 20; i++) {
                Board board = new Board();
                board.setMemberId((long) i);
                //board.setPetSitterId((long) (random.nextInt(5) + 1));

                board.setTitle("Board Title " + i);
                board.setContent("Board Content " + i);

                LocalDateTime startTime = LocalDateTime.of(2023, 9, 16, (random.nextInt(22) + 1), 0); // Start at 8:00 AM
                LocalDateTime endTime = startTime.plusHours(1); // End 1 hour later

                board.setStartTime(startTime);
                board.setEndTime(endTime);

                boardRepository.save(board);
            }

            List<Board> boardList = boardRepository.findAll();
            for (int j = 0; j < boardList.size(); j++) {
                Application application = new Application();
                application.setBoard(boardList.get(j));
                application.setPetSitterId((long) (random.nextInt(5) + 1));
                applicationRepository.save(application);
            }
        };
    }
}
