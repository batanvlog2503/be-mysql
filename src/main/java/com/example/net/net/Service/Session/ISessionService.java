package com.example.net.net.Service.Session;

import com.example.net.net.dto.SessionRequest;
import com.example.net.net.entity.Session;

import java.util.List;

public interface ISessionService {

    List<Session> getAllSessions();

    Session getSessionById(int id);

    Session createSession(Session session);

    Session updateSession(Session session);
}

