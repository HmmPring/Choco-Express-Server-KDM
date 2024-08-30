package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgu.choco_express.domain.choco.Choco;
import lombok.Builder;

@Builder
public record ChocoDetailsResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("nickname")
        String nickname,

        @JsonProperty("contents")
        String contents
) {
    public static ChocoDetailsResponseDto of(Choco choco) {
        return ChocoDetailsResponseDto.builder()
                .id(choco.getId())
                .nickname(choco.getNickname())
                .contents(choco.getContents())
                .build();
    }
}