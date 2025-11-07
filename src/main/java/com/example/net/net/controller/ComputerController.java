package com.example.net.net.controller;


import com.example.net.net.Service.Computer.ComputerService;
import com.example.net.net.Service.Computer.IComputerService;
import com.example.net.net.dto.ComputerDTO;
import com.example.net.net.entity.Computer;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/computers")
public class ComputerController {

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ComputerDTO> getAllComputers(){
       List<Computer> computer = computerService.getAllComputers();
        return modelMapper.map(computer, new TypeToken<List<ComputerDTO>>(){}.getType());

    }
    @GetMapping("/{id}")
    public Computer getComputerById(@PathVariable(name = "id") int id){
        return computerService.getComputerById(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComputer(
            @PathVariable("id") int id,
            @RequestBody Computer computer) {
        computer.setComputerId(id);
        computerService.updateComputer(computer);
        return ResponseEntity.ok("Update successful");
    }



}
