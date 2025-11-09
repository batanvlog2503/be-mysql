package com.example.net.net.Service.Session;

import com.example.net.net.Response.SessionResponseDTO;
import com.example.net.net.entity.Session;
import com.example.net.net.request.SessionRequest;
import com.example.net.net.request.UpdateSessionRequest;

import java.util.List;

public interface ISessionService {

    void createSession(SessionRequest request);
    void updateSession(Integer sessionId, UpdateSessionRequest request);
    void deleteSession(Integer sessionId);


    List<SessionResponseDTO> getAllSessionResponseDTO();
    SessionResponseDTO getSessionResponseById(Integer sessionId);
}

