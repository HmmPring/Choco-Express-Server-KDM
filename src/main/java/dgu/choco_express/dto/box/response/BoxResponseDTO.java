package dgu.choco_express.dto.box.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.box.Box;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoxResponseDTO {

    @JsonProperty("boxId")
    private Long boxId;

    @JsonProperty("boxName")
    private String boxName;

    @JsonProperty("boxType")
    private Short boxType;

    public static BoxResponseDTO of(Box box) {
        return BoxResponseDTO.builder()
                .boxId(box.getId())
                .boxName(box.getName())
                .boxType(box.getType())
                .build();
    }
}
