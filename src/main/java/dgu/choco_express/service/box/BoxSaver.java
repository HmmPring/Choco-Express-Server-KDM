package dgu.choco_express.service.box;

import dgu.choco_express.domain.box.Box;
import dgu.choco_express.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoxSaver {
    private final BoxRepository boxRepository;

    @Transactional
    public Box saveBox(Box box) {
        return boxRepository.save(box);
    }
}
