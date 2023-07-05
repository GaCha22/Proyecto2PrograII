package cr.ac.ucr.ie.prograii.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface SessionService {
    Optional<String> getUser(HttpServletRequest req);
}
