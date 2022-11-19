package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.response.HistoryResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.dto.HistoryResponseDtoService;
import com.kata.cinema.base.service.entity.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/history")
@AllArgsConstructor
public class UserHistoryRestController {

    private final HistoryResponseDtoService historyResponseDtoService;
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryResponseDto>> getHistoryResponseDto(@AuthenticationPrincipal User currentUser) {
        return new ResponseEntity<>(historyResponseDtoService.getHistoryResponseDtoListByUserId(currentUser.getId()), HttpStatus.OK);
    }

    @Scheduled(initialDelayString = "PT30S", fixedDelayString = "P1W")
    @DeleteMapping("/reset")
    private void resetHistory() {
        historyService.deleteHistoryIfPassed30Days();
    }

}
