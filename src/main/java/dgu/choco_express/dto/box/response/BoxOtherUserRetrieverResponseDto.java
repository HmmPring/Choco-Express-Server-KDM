package dgu.choco_express.dto.box.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.box.Box;
import lombok.Builder;

@Builder
public record BoxOtherUserRetrieverResponseDto(
        @JsonProperty("boxId")
        Long boxId,
        @JsonProperty("boxName")
        String boxName,
        @JsonProperty("boxType")
        Short boxType
) {
    public static BoxOtherUserRetrieverResponseDto of(Box box) {
        return BoxOtherUserRetrieverResponseDto.builder()
                                            .boxId(box.getId())
                                            .boxName(box.getName())
                                            .boxType(box.getType())
                                            .build();
    }
}
