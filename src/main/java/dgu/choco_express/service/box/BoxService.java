package dgu.choco_express.service.box;

import dgu.choco_express.domain.Box.Box;
import dgu.choco_express.domain.user.User;
import dgu.choco_express.dto.box.request.BoxCreateRequestDto;
import dgu.choco_express.dto.box.request.BoxPatchRequestDto;
import dgu.choco_express.dto.box.response.BoxCurrentUserRetrieverResponseDto;
import dgu.choco_express.dto.box.response.BoxOtherUserRetrieverResponseDto;
import dgu.choco_express.exception.BoxErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.service.user.UserRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class BoxService {
    private final BoxRetriever boxRetriever;
    private final BoxSaver boxSaver;
    private final UserRetriever userRetriever;
    private final BoxUpdater boxUpdater;

    public URI createBox(
            Long userId,
            BoxCreateRequestDto boxCreateRequestDto
    ) {
        User currentUser = userRetriever.findById(userId);

        String boxName = boxCreateRequestDto.boxName();
        Short boxType = boxCreateRequestDto.boxType();

        if (boxName.isEmpty())
            throw CommonException.type(BoxErrorCode.INVALID_BOX_NAME);
        if (boxType < 1 || boxType > 6)
            throw CommonException.type(BoxErrorCode.INVALID_BOX_TYPE);

        Box createdBox = boxSaver.saveBox(
                Box.from(boxName, boxType, currentUser)
        );

        return URI.create("/api/box/" + createdBox.getId().toString());
    }

    public BoxOtherUserRetrieverResponseDto getOtherUserBox(Long boxId) {
        Box currentBox = boxRetriever.findById(boxId);

        return BoxOtherUserRetrieverResponseDto.of(currentBox);
    }

    public Void updateBox(
            Long userId,
            Long boxId,
            BoxPatchRequestDto boxPatchRequestDto
    ) {
        User currentUser = userRetriever.findById(userId);
        Box currrentBox = boxRetriever.findByIdAndUser(boxId, currentUser);

        String boxName = boxPatchRequestDto.boxName();
        Short boxType = boxPatchRequestDto.boxType();
        if (boxType < 1 || boxType > 6)
            throw CommonException.type(BoxErrorCode.INVALID_BOX_TYPE);
        boxUpdater.updateBox(currrentBox, boxName, boxType);

        return null;
    }

    public BoxCurrentUserRetrieverResponseDto getCurrentUserBox(Long userId) {
        User currentUser = userRetriever.findById(userId);
        Box currentBox = boxRetriever.findByUser(currentUser);

        return BoxCurrentUserRetrieverResponseDto.of(currentBox);
    }
}
