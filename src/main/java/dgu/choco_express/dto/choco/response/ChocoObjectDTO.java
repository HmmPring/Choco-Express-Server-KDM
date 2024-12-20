package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.choco.Choco;
import lombok.Builder;

@Builder
public record ChocoObjectDTO(
        @JsonProperty("id")
        Long id,

        @JsonProperty("chocoType")
        Short chocoType
) {
    public static ChocoObjectDTO of(final Choco choco) {
        return ChocoObjectDTO.builder()
                .id(choco.getId())
                .chocoType(choco.getType())
                .build();
    }
}