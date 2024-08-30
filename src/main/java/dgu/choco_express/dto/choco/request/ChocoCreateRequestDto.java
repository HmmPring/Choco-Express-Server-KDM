package dgu.choco_express.dto.choco.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChocoCreateRequestDto(
        @JsonProperty("nickname")
        String nickname,

        @JsonProperty("contents")
        String contents,

        @JsonProperty("chocoType")
        Short chocoType
) {
}
