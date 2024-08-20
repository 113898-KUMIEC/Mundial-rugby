package ar.edu.utn.frc.tup.lciii.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "poolId", "teams" })
public class MatchesDTO {
    @JsonProperty("pool_id")
    private String poolId;
    private List<TeamsDTO> teams;
}
