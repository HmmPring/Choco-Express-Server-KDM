package dgu.choco_express.service.choco;

import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.choco.Choco;
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

    public Choco findById(Long id) {
        return chocoRepository.findById(id)
                .orElseThrow(() -> CommonException.type(ChocoErrorCode.NOT_FOUND_CHOCO));
    }

    public Page<Choco> findAllByBoxId(Long boxId, Pageable pageable) {
        return chocoRepository.findAllByBoxId(boxId, pageable);
    }

    public Integer findChocoCountByBox(Box box) {
        return chocoRepository.findChocoCountByBox(box);
    }
}
