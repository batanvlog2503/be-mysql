package com.example.net.net.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {
    private Integer customerId;
    private Integer computerId;
    private LocalDateTime startTime = LocalDateTime.now();
//    private LocalDateTime endTime;
//    private Integer total;
}
