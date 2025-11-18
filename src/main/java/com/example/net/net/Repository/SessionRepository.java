package com.example.net.net.Repository;

import com.example.net.net.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findByCustomerId(Integer customerId);
}
