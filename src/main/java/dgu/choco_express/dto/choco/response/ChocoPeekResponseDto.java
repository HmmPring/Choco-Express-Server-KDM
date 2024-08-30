package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.box.Box;
import lombok.Builder;

@Builder
public record ChocoPeekResponseDto(
        @JsonProperty("count")
        Integer count
) {
}

