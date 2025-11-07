package com.example.net.net.entity;


import com.example.net.net.Enum.Status;
import com.example.net.net.Enum.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "computer_id", nullable = false)
    private int computerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('Offline', 'Using', 'Maintenance') DEFAULT 'Offline')")
    private Status status;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;


    // đây là cha thì dùng Join bên kia dùng mapped by
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id", referencedColumnName = "computer_id")
    private InfoComputer infoComputer;

}
