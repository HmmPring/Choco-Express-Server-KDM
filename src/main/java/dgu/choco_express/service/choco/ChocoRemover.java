package dgu.choco_express.service.choco;

import dgu.choco_express.domain.choco.Choco;
import dgu.choco_express.repository.ChocoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChocoRemover {
    // 초코 삭제
    private final ChocoRepository chocoRepository;
    public void removeChoco(Choco choco){
        chocoRepository.delete(choco);
    }
}
