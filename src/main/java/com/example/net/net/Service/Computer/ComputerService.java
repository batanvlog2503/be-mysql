package com.example.net.net.Service.Computer;

import com.example.net.net.Repository.ComputerRepository;
import com.example.net.net.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService implements IComputerService{

    @Autowired
    private ComputerRepository computerRepository;


    @Override
    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    @Override
    public Computer getComputerById(Integer id) {
        return computerRepository.findById(id).get();
    }

    @Override
    public void updateComputer(Computer computer) {
        computerRepository.save(computer);
    }
}
