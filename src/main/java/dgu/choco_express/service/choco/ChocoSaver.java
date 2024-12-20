package dgu.choco_express.service.choco;

import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.repository.ChocoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChocoSaver {
    // 초코 저장
    private final ChocoRepository chocoRepository;
    public Choco saveChoco(Choco choco){
        return chocoRepository.save(choco);
    }
}