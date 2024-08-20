package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    private Long id;
    private LocalDate date;
    private List<TeamInMatch> teams;
    private Long stadium;
    private String pool;
}
