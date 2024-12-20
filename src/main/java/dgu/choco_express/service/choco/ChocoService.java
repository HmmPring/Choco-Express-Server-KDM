package dgu.choco_express.service.choco;

import dgu.choco_express.dto.choco.request.ChocoRequestDTO;
import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.domain.user.User;
import dgu.choco_express.dto.choco.response.ChocoCountDTO;
import dgu.choco_express.dto.choco.response.ChocoDetailDTO;
import dgu.choco_express.dto.choco.response.ChocoListDTO;
import dgu.choco_express.dto.choco.response.ChocoObjectDTO;
import dgu.choco_express.exception.ChocoErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.service.user.UserRetriever;
import dgu.choco_express.service.box.BoxService;
import dgu.choco_express.service.choco.ChocoSaver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChocoService {
    private final UserRetriever userRetriever;
    private final BoxService boxService;
    private final ChocoSaver chocoSaver;
    private final ChocoRetriever chocoRetriever;
    private final ChocoRemover chocoRemover;

    //초코 생성
    public URI createChoco(
            Long userId,
            Long boxId,
            ChocoRequestDTO chocoRequestDTO
    ){
        User currentUser = userRetriever.findById(userId);
        Box currentBox = boxService.findById(boxId);
        String nickname = chocoRequestDTO.nickname();
        String contents = chocoRequestDTO.contents();
        Short chocoType = chocoRequestDTO.chocoType();

        if(nickname.isEmpty())
            throw CommonException.type(ChocoErrorCode.INVALID_CHOCO_NAME);
        if(chocoType < 1 || chocoType > 6)
            throw CommonException.type(ChocoErrorCode.INVALID_CHOCO_TYPE);
        if(currentUser.equals(currentBox.getUser()))
           throw CommonException.type(ChocoErrorCode.CANT_CHOCO_RECURSIVE);

        Choco createChoco = chocoSaver.saveChoco(
                Choco.from(chocoType, nickname, contents, currentBox)
        );

        return URI.create("/api/choco/" + createChoco.getId().toString());
    }
    //초코 엿보기
    public ChocoCountDTO getOtherChoco(
            Long userId
    ){
        User currentUser = userRetriever.findById(userId);
        Box currentBox = boxService.findByUser(currentUser);

        return ChocoCountDTO.builder()
                .count(chocoRetriever.countChoco(currentBox))
                .build();
    }
    //초코 상세정보
    public ChocoDetailDTO getChocoDetail(
            Long userId,
            Long chocoId
    ){
        User currentUser = userRetriever.findById(userId);
        Choco choco = chocoRetriever.findById(chocoId);

        if(!currentUser.equals(choco.getBox().getUser()))
            throw CommonException.type(ChocoErrorCode.INVALID_ACCESS);

        return ChocoDetailDTO.of(choco);
    }
    //리스트 확인
    public ChocoListDTO getChocoList(
            Long userId,
            int page
    ){
        User currentUser = userRetriever.findById(userId);
        Box box =  boxService.findByUser(currentUser);

        if(page < 1)
            throw CommonException.type(ChocoErrorCode.INVALID_PAGE_CHOCO);

        int size = (box.getType() == 4) ? 6 : 9;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Choco> chocoPage
                = chocoRetriever.findChocoList(box.getId(), pageable);

        List<ChocoObjectDTO> chocoObjectDTO = chocoPage.getContent().stream().map(ChocoObjectDTO::of).toList();

        return ChocoListDTO.of(chocoObjectDTO, chocoPage.getTotalPages());

    }
    //초코 삭제
    public Void removeChoco(Long userId, Long chocoId){
        User currentUser = userRetriever.findById(userId);
        Choco choco = chocoRetriever.findById(chocoId);

        if(!currentUser.equals(choco.getBox().getUser()))
            throw CommonException.type(ChocoErrorCode.INVALID_ACCESS);

        chocoRemover.removeChoco(choco);

        return null;
    }

}
