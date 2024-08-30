package dgu.choco_express.service.choco;

import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.domain.user.User;
import dgu.choco_express.dto.choco.request.ChocoCreateRequestDto;
import dgu.choco_express.dto.choco.response.ChocoDetailsResponseDto;
import dgu.choco_express.dto.choco.response.ChocoListResponseDto;
import dgu.choco_express.dto.choco.response.ChocoObjectResponseDto;
import dgu.choco_express.dto.choco.response.ChocoPeekResponseDto;
import dgu.choco_express.exception.ChocoErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.service.box.BoxRetriever;
import dgu.choco_express.service.user.UserRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChocoService {
    private final UserRetriever userRetriever;
    private final ChocoSaver chocoSaver;
    private final BoxRetriever boxRetriever;
    private final ChocoRetriever chocoRetriever;
    private final ChocoRemover chocoRemover;

    public URI createChoco(
            Long userId,
            Long boxId,
            ChocoCreateRequestDto chocoCreateRequestDto
    ) {
        User currentUser = userRetriever.findById(userId);

        Short chocoType = chocoCreateRequestDto.chocoType();
        String chocoNickname = chocoCreateRequestDto.nickname();
        String chocoContents = chocoCreateRequestDto.contents();
        Box currentBox = boxRetriever.findById(boxId);


        if (chocoType < 1 || chocoType > 6)
            throw CommonException.type(ChocoErrorCode.INVALID_CHOCO_TYPE);
        if (chocoNickname.isEmpty())
            throw CommonException.type(ChocoErrorCode.INVALID_CHOCO_NAME);

        Choco createdChoco = chocoSaver.saveChoco(
            Choco.from(chocoType, chocoNickname, chocoContents, currentBox)
        );

        return URI.create("/api/choco/" + createdChoco.getId().toString());
    }

    public ChocoListResponseDto getChocoList(
            Long userId,
            int page
    ) {
        User currentUser = userRetriever.findById(userId);
        Box box = boxRetriever.findByUser(currentUser);

        if (page < 1)
            throw CommonException.type(ChocoErrorCode.INVALID_PAGE_CHOCO);

        int size = (box.getType() == 4) ? 6 : 9;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Choco> chocoPage
                = chocoRetriever.findAllByBoxId(box.getId(), pageable);

        if (page != 1 && page > chocoPage.getTotalPages())
            throw CommonException.type(ChocoErrorCode.INVALID_PAGE_CHOCO);

        List<ChocoObjectResponseDto> chocoObjectResponseDtoList
                = chocoPage.getContent().stream()
                                        .map(ChocoObjectResponseDto::of)
                                        .toList();

        return ChocoListResponseDto.of(
                chocoObjectResponseDtoList,
                chocoPage.getTotalPages()
        );
    }

    public ChocoDetailsResponseDto getChocoDetails(
            Long userId,
            Long chocoId
    ) {
        User currentUser = userRetriever.findById(userId);
        Choco choco = chocoRetriever.findById(chocoId);

        return ChocoDetailsResponseDto.of(choco);
    }

    public ChocoPeekResponseDto getChocoPeek(
            Long userId
    ) {
        User currentUser = userRetriever.findById(userId);
        Box currentBox = boxRetriever.findByUser(currentUser);

        return ChocoPeekResponseDto.builder()
                .count(chocoRetriever.findChocoCountByBox(currentBox))
                .build();
    }

    public Void deleteChoco(
            Long userId,
            Long chocoId
    ) {
        User currentUser = userRetriever.findById(userId);
        Choco choco = chocoRetriever.findById(chocoId);

        chocoRemover.deleteChoco(choco);

        return null;
    }
}
