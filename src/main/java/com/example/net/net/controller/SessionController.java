package com.example.net.net.controller;

import com.example.net.net.Service.Session.ISessionService;
import com.example.net.net.dto.SessionRequest;
import com.example.net.net.entity.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sessions")
//@CrossOrigin("http://localhost:5173")
public class SessionController {

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Session> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public Session getSessionById(@PathVariable(name = "id") int id){
        return sessionService.getSessionById(id);
    }

    // ✅ QUAN TRỌNG: Phải trả về Session đã được tạo (có sessionId)
    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session){
        Session createdSession = sessionService.createSession(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Session> updateSession(
            @PathVariable(name = "id") int id,
            @RequestBody Session session){
        session.setSessionId(id);
        Session updatedSession = sessionService.updateSession(session);
        return ResponseEntity.ok(updatedSession);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSession(@PathVariable(name = "id") int id){
//        sessionService.deleteSession(id);
//        return ResponseEntity.noContent().build();
//    }
}