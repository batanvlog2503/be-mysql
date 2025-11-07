package com.example.net.net.Repository;

import com.example.net.net.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
}
