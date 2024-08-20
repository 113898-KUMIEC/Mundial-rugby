package ar.edu.utn.frc.tup.lciii.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teams {
    private Long id;
    private String name;
    private String country;
    @JsonProperty("world_ranking")
    private Long worldRanking;
    private String pool;
}
