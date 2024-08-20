package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.dtos.common.MatchesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchesService {
    MatchesDTO getMatchById(String poolId);

    List<MatchesDTO> getAllMatch();
}
