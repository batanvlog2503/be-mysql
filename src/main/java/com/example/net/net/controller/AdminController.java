package com.example.net.net.controller;

import com.example.net.net.Service.Admin.AdminService;
import com.example.net.net.entity.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/{id}")

    public ResponseEntity<Admin> getAdminById(@PathVariable(name = "id") Integer id){

        try{
            Admin admin = adminService.getAdminById(id);
            return ResponseEntity.ok(admin);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND). build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
