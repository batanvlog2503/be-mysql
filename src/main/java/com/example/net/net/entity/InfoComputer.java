package com.example.net.net.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "infocomputer")
public class InfoComputer {

    @Id
    @Column(name = "computer_id", nullable = false)
    private Integer computerId;

    @Column(name = "screen", length = 200, nullable = false)
    private String screen;
    @Column(name = "chip", length = 200, nullable = false)
    private String chip;
    @Column(name = "gpu", length = 200, nullable = false)
    private String gpu;
    @Column(name = "mouse", length = 200, nullable = false)
    private String mouse;
    @Column(name = "keyboard", length = 200, nullable = false)
    private String keyboard;

    //  ánh xạ ngược về Computer
    @OneToOne(mappedBy = "infoComputer")
    private Computer computer;


}
