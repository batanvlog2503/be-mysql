package com.example.net.net.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateServiceRequest {

    private Integer customerId;   // nếu muốn đổi customer
    private Integer sessionId;
}
