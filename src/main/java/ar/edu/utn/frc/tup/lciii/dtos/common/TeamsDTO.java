package ar.edu.utn.frc.tup.lciii.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "team_id", "team_name", "country", "matches_played", "wins", "draws", "losses", "points_for", "points_against", "points_differential", "tries_made", "bonus_points", "points", "total_yellow_cards", "total_red_cards" })
public class TeamsDTO {
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("team_name")
    private String teamName;
    private String country;
    @JsonProperty("matches_played")
    private Long matchesPlayed;
    private Long wins;
    private Long draws;
    private Long losses;
    @JsonProperty("points_for")
    private Long pointsFor;
    @JsonProperty("points_against")
    private Long pointsAgainst;
    @JsonProperty("points_differential")
    private Long pointsDifferential;
    @JsonProperty("tries_made")
    private Long triesMade;
    @JsonProperty("bonus_points")
    private Long bonusPoints;
    private Long points;
    @JsonProperty("total_yellow_cards")
    private Long totalYellowCards;
    @JsonProperty("total_red_cards")
    private Long totalRedCards;
}
