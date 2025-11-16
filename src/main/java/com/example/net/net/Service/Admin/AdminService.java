package com.example.net.net.Service.Admin;

import com.example.net.net.Repository.AdminRepository;
import com.example.net.net.entity.Admin;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService{
    @Autowired

    private AdminRepository adminRepository;
    @Override
    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin Not FOund" + id));

    }
}
