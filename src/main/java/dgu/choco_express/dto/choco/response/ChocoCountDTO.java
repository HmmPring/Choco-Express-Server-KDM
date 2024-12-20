package dgu.choco_express.dto.choco.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

//초콜릿 엿보기
@Builder
public record ChocoCountDTO(
        @JsonProperty("count") Integer count
) {
}
