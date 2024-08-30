package dgu.choco_express.controller;

import dgu.choco_express.annotation.UserId;
import dgu.choco_express.dto.choco.request.ChocoCreateRequestDto;
import dgu.choco_express.service.choco.ChocoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChocoController {
    private final ChocoService chocoService;

    @PostMapping("/box/{boxId}/choco")
    public ResponseEntity<?> createChoco(
            @UserId Long userId,
            @PathVariable Long boxId,
            @RequestBody ChocoCreateRequestDto chocoCreateRequestDto
            ) {
        return ResponseEntity.created(
                chocoService.createChoco(userId, boxId, chocoCreateRequestDto)
        ).build();
    }

    @GetMapping("/choco")
    public ResponseEntity<?> getChocoList(
            @UserId Long userId,
            @RequestParam(defaultValue = "1") int page
    ) {
        return ResponseEntity.ok(chocoService.getChocoList(userId, page));
    }

    @GetMapping("/choco/{chocoId}")
    public ResponseEntity<?> getChocoDetails(
            @UserId Long userId,
            @PathVariable Long chocoId
    ) {
        return ResponseEntity.ok(
                chocoService.getChocoDetails(userId, chocoId)
        );
    }

    @GetMapping("/choco/count")
    public ResponseEntity<?> getChocoPeek(
            @UserId Long userId
    ) {
        return ResponseEntity.ok(chocoService.getChocoPeek(userId));
    }

    @DeleteMapping("/choco/{chocoId}")
    public ResponseEntity<?> deleteChoco(
            @UserId Long userId,
            @PathVariable Long chocoId
    ) {
        return ResponseEntity.ok(chocoService.deleteChoco(userId, chocoId));
    }
}
