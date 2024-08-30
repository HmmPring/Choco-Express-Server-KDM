package dgu.choco_express.service.box;

import dgu.choco_express.domain.Box.Box;
import dgu.choco_express.domain.user.User;
import dgu.choco_express.exception.BoxErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoxRetriever {
    private final BoxRepository boxRepository;

    public Box findById(Long id) {
        return boxRepository.findById(id)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.NOT_FOUND_BOX));
    }

    public Box findByIdAndUser(Long boxId, User user) {
        return boxRepository.findByIdAndUser(boxId, user)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.MISMATCH_USER_AND_BOX_ID));
    }

    public Box findByUser(User user) {
        return boxRepository.findByUser(user)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.YET_USER_HAS_BOX));
    }
}
