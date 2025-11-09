package com.example.net.net.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ComputerDTO {

    private Integer computerId;
    private String status;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class InfocomputerDTO{
        private Integer computerId;

        private String screen;
        private String chip;
        private String gpu;
        private String mouse;
        private String keyboard;

    }

    private InfocomputerDTO infoComputer;
}
