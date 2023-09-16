package seb45.test.pettalk.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimeDto {

    LocalDateTime startTime;
    LocalDateTime endTime;
}
