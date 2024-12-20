package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import dgu.choco_express.domain.choco.Choco;

// 초콜릿 상세정보
@Builder
public record ChocoDetailDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("nickname") String nickname,
        @JsonProperty("contents") String contents
        ) {
    public static ChocoDetailDTO of(Choco choco){
        return ChocoDetailDTO.builder()
                .id(choco.getId())
                .nickname(choco.getNickname())
                .contents(choco.getContents())
                .build();
    }
}
