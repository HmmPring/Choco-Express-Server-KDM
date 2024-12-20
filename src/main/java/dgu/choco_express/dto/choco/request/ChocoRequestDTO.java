package dgu.choco_express.dto.choco.request;

import com.fasterxml.jackson.annotation.JsonProperty;
// 초콜릿 보내기
public record ChocoRequestDTO(
    @JsonProperty("nickname") String nickname,
    @JsonProperty("contents") String contents,
    @JsonProperty("chocoType") Short chocoType
){}
