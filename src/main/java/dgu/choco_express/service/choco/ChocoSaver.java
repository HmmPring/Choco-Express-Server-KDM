package dgu.choco_express.service.choco;

import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.repository.ChocoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChocoSaver {
    private final ChocoRepository chocoRepository;

    @Transactional
    public Choco saveChoco(Choco choco) {
        return chocoRepository.save(choco);
    }
}
