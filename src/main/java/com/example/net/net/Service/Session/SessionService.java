package com.example.net.net.Service.Session;

import com.example.net.net.Repository.ComputerRepository;
import com.example.net.net.Repository.CustomerRepository;
import com.example.net.net.Repository.SessionRepository;
import com.example.net.net.dto.SessionRequest;
import com.example.net.net.entity.Computer;
import com.example.net.net.entity.Customer;
import com.example.net.net.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SessionService implements ISessionService{
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSessionById(int id) {
        return sessionRepository.findById(id).get();
    }

    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(Session session) {
        // save() cũng dùng để update
        return sessionRepository.save(session);
    }


}
