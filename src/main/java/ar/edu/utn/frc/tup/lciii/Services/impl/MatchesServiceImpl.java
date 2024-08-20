package ar.edu.utn.frc.tup.lciii.Services.impl;

import ar.edu.utn.frc.tup.lciii.Models.Matches;
import ar.edu.utn.frc.tup.lciii.Models.TeamInMatch;
import ar.edu.utn.frc.tup.lciii.Models.Teams;
import ar.edu.utn.frc.tup.lciii.Services.MatchesService;
import ar.edu.utn.frc.tup.lciii.clients.RestClient;
import ar.edu.utn.frc.tup.lciii.dtos.common.MatchesDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.TeamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchesServiceImpl implements MatchesService {
    private final RestClient restClient;

    @Autowired
    public MatchesServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Matches> getMatches(){
        return restClient.getAllMatches().getBody();
    }
    public List<Teams> getTeams(){
        return restClient.getAllTeams().getBody();
    }
    @Override
    public MatchesDTO getMatchById(String poolId) {

        // Obtener listas de datos
        List<Teams> teams = getTeams();

        // Inicializar el objeto DTO
        MatchesDTO matchesDTO = new MatchesDTO();
        List<TeamsDTO> teamsInMatch = new ArrayList<>();
        matchesDTO.setTeams(teamsInMatch);
        matchesDTO.setPoolId(poolId);

        // Iterar sobre equipos
        for (Teams team : teams) {
            if (team.getPool().equals(poolId)) {
                TeamsDTO teamDTO = createTeamDTO(team);
                teamsInMatch.add(teamDTO);
            }
        }

        return matchesDTO;
    }

    private TeamsDTO createTeamDTO(Teams team) {
        TeamsDTO teamDTO = new TeamsDTO();
        teamDTO.setTeamId(team.getId());
        teamDTO.setTeamName(team.getName());
        teamDTO.setCountry(team.getCountry());
        teamDTO.setMatchesPlayed(countMatchesPlayed(team.getId()));
        teamDTO.setWins(countTeamWin(team.getId()));
        teamDTO.setDraws(countTeamDraw(team.getId()));
        teamDTO.setLosses(countTeamLosses(team.getId()));
        teamDTO.setPointsFor(countTeamPointsFor(team.getId()));
        teamDTO.setPointsAgainst(countTeamPointsAgainst(team.getId()));
        teamDTO.setPointsDifferential(countTeamPointsFor(team.getId()) - countTeamPointsAgainst(team.getId()));
        teamDTO.setTriesMade(countTeamTriesMade(team.getId()));
        teamDTO.setBonusPoints(countTriesBonusPoint(team.getId()));
        teamDTO.setPoints(countTeamPoints(team.getId()));
        teamDTO.setTotalYellowCards(countTotalYellowCards(team.getId()));
        teamDTO.setTotalRedCards(countTotalRedCards(team.getId()));
        return teamDTO;
    }


    @Override
    public List<MatchesDTO> getAllMatch() {
        List<MatchesDTO> matchesDTO = new ArrayList<>();
        matchesDTO.add(getMatchById("A"));
        matchesDTO.add(getMatchById("B"));
        matchesDTO.add(getMatchById("C"));
        matchesDTO.add(getMatchById("D"));
        return matchesDTO;
    }

    private Long countTotalYellowCards(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            for (TeamInMatch team: match.getTeams()){
                if (team.getId().equals(id)){
                    cantidad += team.getYellowCards();
                }
            }
        }
        return cantidad;
    }

    private Long countTotalRedCards(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            for (TeamInMatch team: match.getTeams()){
                if (team.getId().equals(id)){
                    cantidad += team.getRedCards();
                }
            }
        }
        return cantidad;
    }

    private Long countTeamPoints(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id)){
                if (team1.getPoints() > team2.getPoints()){
                    cantidad += 4;
                } else if (team1.getPoints().equals(team2.getPoints())) {
                    cantidad += 2;
                }
            }else if (team2.getId().equals(id)){
                if (team2.getPoints() > team1.getPoints()){
                    cantidad += 4;
                } else if (team2.getPoints().equals(team1.getPoints())) {
                    cantidad += 2;
                }
            }
        }
        return cantidad;
    }

    private Long countTriesBonusPoint(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id)){
                if (team1.getTries()>=4){
                    cantidad++;
                }
                if (team1.getPoints()<team2.getPoints() && team2.getPoints()-team1.getPoints() <= 7){
                    cantidad++;
                }
            }else if (team2.getId().equals(id)){
                if (team2.getTries()>=4){
                    cantidad++;
                }
                if (team2.getPoints()<team1.getPoints() && team1.getPoints()-team2.getPoints() <= 7){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    private Long countTeamPointsFor(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            for (TeamInMatch team: match.getTeams()){
                if (team.getId().equals(id)){
                    cantidad += team.getPoints();
                }
            }
        }
        return cantidad;
    }

    private Long countTeamTriesMade(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            for (TeamInMatch team: match.getTeams()){
                if (team.getId().equals(id)){
                    cantidad += team.getTries();
                }
            }
        }
        return cantidad;
    }
    private Long countTeamPointsAgainst(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id)){
                cantidad += team2.getPoints();
            }else if (team2.getId().equals(id)){
                cantidad += team1.getPoints();
            }
        }
        return cantidad;
    }

    private Long countTeamWin(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id) && team1.getPoints() > team2.getPoints()){
                cantidad++;
            }else if (team2.getId().equals(id) && team2.getPoints() > team1.getPoints()){
                cantidad++;
            }
        }
        return cantidad;
    }

    private Long countTeamDraw(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id) && team1.getPoints().equals(team2.getPoints())){
                cantidad++;
            }else if (team2.getId().equals(id) && team2.getPoints().equals(team1.getPoints())){
                cantidad++;
            }
        }
        return cantidad;
    }

    private Long countTeamLosses(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            TeamInMatch team1 = match.getTeams().get(0);
            TeamInMatch team2 = match.getTeams().get(1);

            if (team1.getId().equals(id) && team1.getPoints() < team2.getPoints()){
                cantidad++;
            }else if (team2.getId().equals(id) && team2.getPoints() < team1.getPoints()){
                cantidad++;
            }
        }
        return cantidad;
    }

    private Long countMatchesPlayed(Long id) {
        Long cantidad = 0L;
        List<Matches> matches = getMatches();
        for (Matches match:matches){
            for (TeamInMatch team: match.getTeams()){
                if (team.getId().equals(id)){
                    cantidad ++;
                }
            }
        }
        return cantidad;
    }
}
