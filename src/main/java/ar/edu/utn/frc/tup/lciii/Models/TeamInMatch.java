package ar.edu.utn.frc.tup.lciii.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInMatch {
    private Long id;
    private Long points;
    private Long tries;
    @JsonProperty("yellow_cards")
    private Long yellowCards;
    @JsonProperty("red_cards")
    private Long redCards;
}
