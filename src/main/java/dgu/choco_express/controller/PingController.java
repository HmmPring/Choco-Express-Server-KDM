package dgu.choco_express.controller;

import dgu.choco_express.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PingController {

    private final JwtService jwtService;

    @PostMapping("/token")
    public ResponseEntity<?> getAuthorization() {
        return ResponseEntity.ok(jwtService.getAuthenticationToken());
    }
}
