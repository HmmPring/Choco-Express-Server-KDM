package dgu.choco_express.controller;

import dgu.choco_express.annotation.UserId;
import dgu.choco_express.dto.box.request.BoxRequestDTO;
import dgu.choco_express.service.box.BoxService;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box")
public class BoxController {
    private final BoxService boxService;

    @PostMapping
    public ResponseEntity<?> createBox(
            @UserId Long userId,
            @RequestBody BoxRequestDTO boxRequestDTO
    ) {
        return ResponseEntity.created(
                boxService.createBox(userId, boxRequestDTO)
        ).build();
    }

    @PutMapping("/{boxId}")
    public ResponseEntity<?> updateBox(
            @UserId Long userId,
            @PathVariable Long boxId,
            @RequestBody BoxRequestDTO boxRequestDTO
    ){
        return ResponseEntity.ok(boxService.updateBox(userId, boxId, boxRequestDTO));
    }

    @GetMapping("/user-box")
    public ResponseEntity<?> getUserBox(
            @UserId Long userId
    ){
        return ResponseEntity.ok(boxService.getUserBox(userId));
    }

    @GetMapping("/{boxId}")
    public ResponseEntity<?> getOtherBox(
            @PathVariable Long boxId
    ){
        return ResponseEntity.ok(boxService.getBox(boxId));
    }
}
