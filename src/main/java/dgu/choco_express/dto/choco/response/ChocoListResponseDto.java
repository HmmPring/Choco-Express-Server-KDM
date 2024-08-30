package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record ChocoListResponseDto(
        @JsonProperty("chocoList")
        List<ChocoObjectResponseDto> chocoObjectResponseDtoList,

        @JsonProperty("totalPage")
        Integer totalPage
) {
        public static ChocoListResponseDto of(
                final List<ChocoObjectResponseDto> chocoObjectResponseDtoList,
                final Integer totalPage
        ) {
                return ChocoListResponseDto.builder()
                        .chocoObjectResponseDtoList(chocoObjectResponseDtoList)
                        .totalPage(totalPage)
                        .build();
        }
}
