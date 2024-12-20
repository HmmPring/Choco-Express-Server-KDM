package dgu.choco_express.dto.box.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BoxRequestDTO(
        @JsonProperty("boxName") String boxName,
        @JsonProperty("boxType") Short boxType
) { }
