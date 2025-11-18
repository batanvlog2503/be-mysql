package com.example.net.net.controller;

import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.Response.SessionResponseDTO;
import com.example.net.net.Service.Session.ISessionService;
import com.example.net.net.entity.Session;
import com.example.net.net.request.SessionRequest;
import com.example.net.net.request.UpdateSessionRequest;
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
    public ResponseEntity<List<SessionResponseDTO>> getAllSessionsResponseDTO(){
        try{
            List<SessionResponseDTO> response = sessionService.getAllSessionResponseDTO();
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> getSessionResponseById(@PathVariable Integer id) {
        try {
             SessionResponseDTO response = sessionService.getSessionResponseById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SessionResponseDTO>> getSessionsByCustomerId(@PathVariable Integer customerId){
        try {
            List<SessionResponseDTO> response = sessionService.getSessionsByCustomerId(customerId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // create session
    @PostMapping
    public ResponseEntity<?> createSession(@RequestBody SessionRequest request    ){
        try {
            Session createdSession = sessionService.createSession(request);
            return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // update sesion
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSession(
            @PathVariable(name = "id") Integer id,
            @RequestBody UpdateSessionRequest request){
        try {
            SessionResponseDTO updateSession = sessionService.updateSession(id, request);
            return new ResponseEntity<>(updateSession, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Integer id) {
        try {
           sessionService.deleteSession(id);
            return ResponseEntity.ok("Session deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }

}