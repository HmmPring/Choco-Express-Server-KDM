package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.choco.Choco;
import lombok.Builder;

@Builder
public record ChocoObjectResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("chocoType")
        Short chocoType
) {
        public static ChocoObjectResponseDto of(final Choco choco) {
                return ChocoObjectResponseDto.builder()
                        .id(choco.getId())
                        .chocoType(choco.getType())
                        .build();
        }
}
