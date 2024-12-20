package dgu.choco_express.controller;

import dgu.choco_express.annotation.UserId;
import dgu.choco_express.dto.choco.request.ChocoRequestDTO;
import dgu.choco_express.dto.choco.response.ChocoCountDTO;
import dgu.choco_express.service.choco.ChocoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChocoController {
    public final ChocoService chocoService;

    @PostMapping("/box/{boxId}/choco")
    public ResponseEntity<?> createChoco(
            @UserId Long userId,
            @PathVariable Long boxId,
            @RequestBody ChocoRequestDTO chocoRequestDTO
            ){
    return ResponseEntity.created(chocoService.createChoco(userId, boxId, chocoRequestDTO))
            .build();
    }

    @GetMapping("/choco")
    public ResponseEntity<?> getChocoList(
            @UserId Long userId,
            @RequestParam(defaultValue = "1") int page
    ){
        return ResponseEntity.ok(chocoService.getChocoList(userId, page));
    }

    @GetMapping("/choco/{chocoId}")
    public ResponseEntity<?> getChocoDetail(
            @UserId Long userId,
            @PathVariable Long chocoId
    ){
        return ResponseEntity.ok(chocoService.getChocoDetail(userId, chocoId));
    }

    @GetMapping("/choco/count")
    public ResponseEntity<?> getChocoCount(
            @UserId Long userId
            ){
        return ResponseEntity.ok(chocoService.getOtherChoco(userId));
    }

    @DeleteMapping("/choco/{chocoId}")
    public ResponseEntity<?> deleteChoco(
            @UserId Long userId,
            @PathVariable Long chocoId
    ){
        chocoService.removeChoco(userId, chocoId);
        return ResponseEntity.noContent()
                .build();
    }

}
