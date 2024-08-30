package dgu.choco_express.dto.box.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BoxCreateRequestDto(
        @JsonProperty("boxName")
        String boxName,
        @JsonProperty("boxType")
        Short boxType
) {
}
