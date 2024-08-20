package ar.edu.utn.frc.tup.lciii.clients;

import ar.edu.utn.frc.tup.lciii.Models.Matches;
import ar.edu.utn.frc.tup.lciii.Models.Teams;
import ar.edu.utn.frc.tup.lciii.dtos.common.MatchesDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.TeamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestClient {

    @Autowired
    RestTemplate restTemplate;
    private static final String URL = "https://my-json-server.typicode.com/LCIV-2023/fake-api-rwc2023";

    public ResponseEntity<List<Matches>> getAllMatches(){
        return restTemplate.exchange(URL + "/matches", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Matches>>() {});
    }
    public ResponseEntity<List<Teams>> getAllTeams(){
        return restTemplate.exchange(URL + "/teams", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Teams>>() {});
    }
}
