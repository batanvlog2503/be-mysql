package com.example.net.net.Service.Session;

import com.example.net.net.Repository.ComputerRepository;
import com.example.net.net.Repository.CustomerRepository;
import com.example.net.net.Repository.SessionRepository;
import com.example.net.net.Response.SessionResponseDTO;
import com.example.net.net.converter.SessionConverter;
import com.example.net.net.entity.Session;
import com.example.net.net.request.SessionRequest;
import com.example.net.net.request.UpdateSessionRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService implements ISessionService{
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionConverter sessionConverter;

    @Override
    public List<SessionResponseDTO> getAllSessionResponseDTO(){
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(sessionConverter::convertSessionToSessionResponseDTO).collect(Collectors.toList());
    }

    @Override
    public SessionResponseDTO getSessionResponseById(Integer id){
        Session session = sessionRepository.findById(id).orElseThrow(()-> new RuntimeException("Session not found by id" + id));

        return sessionConverter.convertSessionToSessionResponseDTO(session);
    }


    @Override
    @Transactional
    public void updateSession(Integer sessionId, UpdateSessionRequest request) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found by id" + sessionId));


        // Cập nhật các trường từ request
        if (request.getCustomerId() != null && request.getCustomerId() != 0) {
            session.setCustomerId(request.getCustomerId());
        }
        if (request.getComputerId() != null && request.getComputerId() != 0) {
            session.setComputerId(request.getComputerId());
        }
        if (request.getEndTime() != null) {
            session.setEndTime(request.getEndTime());
        }
        if (request.getTotal() != null) {
            session.setTotal(request.getTotal());
        }
       // com.example.net.net.entity.Service service = serviceConverter.convertServiceRequestToEntity(request);
        // Nếu bạn muốn cho phép cập nhật startTime, thêm trường vào DTO:
        // if (request.getStartTime() != null) {
        //     session.setStartTime(request.getStartTime());
        // }

        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void createSession(SessionRequest request){
        if(request.getCustomerId() == null){
            throw new IllegalArgumentException("Customer can not be null");
        }
        if(request.getComputerId() == null){
            throw new IllegalArgumentException("Computer cannot be null");

        }

    }
    @Override
    @Transactional
    public void deleteSession(Integer sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(()-> new RuntimeException("Session Not Found Id" + sessionId));

        sessionRepository.delete(session);
    }

}
