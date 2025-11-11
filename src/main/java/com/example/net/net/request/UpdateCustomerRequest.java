package com.example.net.net.request;

import com.example.net.net.Enum.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateCustomerRequest {

    private String username;
    private String password;
    private Type type;
    private Integer balance;
}
