package dgu.choco_express.service.box;

import dgu.choco_express.domain.Box.Box;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BoxUpdater {

    @Transactional
    public void updateBox(
            Box box,
            final String name,
            final Short type
    ) {
        box.updateBox(name, type);
    }
}
