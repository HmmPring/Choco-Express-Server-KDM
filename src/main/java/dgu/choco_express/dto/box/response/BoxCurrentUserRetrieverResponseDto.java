package dgu.choco_express.dto.box.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.box.Box;
import lombok.Builder;

@Builder
public record BoxCurrentUserRetrieverResponseDto(
        @JsonProperty("boxId")
        Long boxId,
        @JsonProperty("boxName")
        String boxName,
        @JsonProperty("boxType")
        Short boxType
) {
    public static BoxCurrentUserRetrieverResponseDto of(Box box) {
        return BoxCurrentUserRetrieverResponseDto.builder()
                                                 .boxId(box.getId())
                                                 .boxName(box.getName())
                                                 .boxType(box.getType())
                                                 .build();
    }
}
