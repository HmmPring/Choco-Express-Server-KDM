package dgu.choco_express.controller;


import dgu.choco_express.annotation.UserId;
import dgu.choco_express.dto.box.request.BoxCreateRequestDto;
import dgu.choco_express.dto.box.request.BoxPatchRequestDto;
import dgu.choco_express.service.box.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box")
public class BoxController {
    private final BoxService boxService;

    @PostMapping
    public ResponseEntity<?> createBox(
            @UserId Long userId,
            @RequestBody BoxCreateRequestDto boxCreateRequestDto
    ) {

        return ResponseEntity.created(
                boxService.createBox(userId, boxCreateRequestDto)
        ).build();
    }

    @GetMapping("/{boxId}")
    public ResponseEntity<?> getOtherUserBox(
            @PathVariable Long boxId
    ) {
        return ResponseEntity.ok(boxService.getOtherUserBox(boxId));
    }

    @PatchMapping("/{boxId}")
    public ResponseEntity<?> updateBox(
            @UserId Long userId,
            @PathVariable Long boxId,
            @RequestBody BoxPatchRequestDto boxPatchRequestDto
    ) {
        return ResponseEntity.ok(boxService.updateBox(userId, boxId, boxPatchRequestDto));
    }

    @GetMapping("user-box")
    public ResponseEntity<?> getCurrentUserBox(@UserId Long userId) {
        return ResponseEntity.ok(boxService.getCurrentUserBox(userId));
    }
}
