package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record ChocoListDTO(
        @JsonProperty("chocoList")
        List<ChocoObjectDTO> chocoObjectDTO,

        @JsonProperty("totalPage")
        Integer totalPage
) {
    public static ChocoListDTO of(
            final List<ChocoObjectDTO> chocoObjectDTO,
            final Integer totalPage
    ) {
        return ChocoListDTO.builder()
                .chocoObjectDTO(chocoObjectDTO)
                .totalPage(totalPage)
                .build();
    }
}