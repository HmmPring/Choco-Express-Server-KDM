package dgu.choco_express.service.choco;

import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.domain.box.Box;
import dgu.choco_express.exception.ChocoErrorCode;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.repository.ChocoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChocoRetriever {
    private final ChocoRepository chocoRepository;
    // 초코 목록 조회 -> 박스마다
    public Page<Choco> findChocoList(Long boxId, Pageable page) {
        return chocoRepository.findAllByBoxId(boxId, page);
    }
    // 초코 조회 -> ID로
    public Choco findById(Long id) {
        return chocoRepository.findById(id)
                .orElseThrow(() -> CommonException.type(ChocoErrorCode.NOT_FOUND_CHOCO));
    }
    // 초코 카운트
    public Integer countChoco(Box box) {
        return chocoRepository.findChocoCount(box);
    }
}
