package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.enums.FormatType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieScheduleResponseDto {

    private Long id;
    private Long movieTicketId;
    private Long addressId;
    private FormatType formatType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime dateTime;
    private Integer price;
}
