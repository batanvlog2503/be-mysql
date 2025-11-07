package com.example.net.net.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessionRequest {
    private int customerId;
    private int computerId;
    private LocalDateTime startTime;
}
