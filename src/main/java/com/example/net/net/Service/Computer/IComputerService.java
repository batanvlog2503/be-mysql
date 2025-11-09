package com.example.net.net.Service.Computer;

import com.example.net.net.entity.Computer;

import java.util.List;

public interface IComputerService {

    List<Computer> getAllComputers();

    Computer getComputerById(Integer id);
    void updateComputer(Computer computer);
}
