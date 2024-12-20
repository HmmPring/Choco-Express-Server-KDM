package dgu.choco_express.service.box;

import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.user.User;
import dgu.choco_express.dto.box.request.BoxRequestDTO;
import dgu.choco_express.dto.box.response.BoxResponseDTO;
import dgu.choco_express.exception.BoxErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.repository.BoxRepository;
import dgu.choco_express.service.user.UserRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Component
@RequiredArgsConstructor
public class BoxService {
    private final BoxRepository boxRepository;
    private final UserRetriever userRetriever;

    public Box findById(Long id){
        return boxRepository.findById(id)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.NOT_FOUND_BOX));
    }

    public Box findByIdAndUser(Long boxId, Long userId){
        return boxRepository.findByIdAndUserId(boxId, userId)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.MISMATCH_USER_AND_BOX_ID));
    }

    public Box findByUser(User user){
        return boxRepository.findByUser(user)
                .orElseThrow(() -> CommonException.type(BoxErrorCode.YET_USER_HAS_BOX));
    }

    public Box saveBox(Box box){
        return boxRepository.save(box);
    }

    @Transactional
    public void updateBoxes(
            Box box,
            final String name,
            final Short type
    ){
        box.updateBox(name, type);
    }

    public URI createBox(
            Long userId,
            BoxRequestDTO boxRequestDTO
    )
    {
        User currentUser = userRetriever.findById(userId);

        String boxName = boxRequestDTO.boxName();
        Short boxType = boxRequestDTO.boxType();

        if(boxName.isEmpty()){
            throw CommonException.type(BoxErrorCode.INVALID_BOX_NAME);
        }
        if(boxType < 1 || boxType > 6){
            throw CommonException.type(BoxErrorCode.INVALID_BOX_TYPE);
        }

        Box createBox = saveBox(
                Box.from(boxName, boxType, currentUser)
        );

        return URI.create("/api/box" + createBox.getId());
    }

    @Transactional
    public Void updateBox(
            Long userId,
            Long boxId,
            BoxRequestDTO boxRequestDTO
    ){
        Box currentBox = findByIdAndUser(boxId, userId);
        String boxName = boxRequestDTO.boxName();
        Short boxType = boxRequestDTO.boxType();

        if(boxType < 1 || boxType > 6){
            throw CommonException.type(BoxErrorCode.INVALID_BOX_TYPE);
        }
        updateBoxes(currentBox, boxName, boxType);
        return null;
    }

    public BoxResponseDTO getUserBox(Long userId) {
        User currentUser = userRetriever.findById(userId);
        Box currentBox = findByUser(currentUser);

        return BoxResponseDTO.of(currentBox);
    }

    public BoxResponseDTO getBox(Long boxId){
        Box currentBox = findById(boxId);

        return BoxResponseDTO.of(currentBox);
    }
}
