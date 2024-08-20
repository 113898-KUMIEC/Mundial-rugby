package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.Services.MatchesService;
import ar.edu.utn.frc.tup.lciii.dtos.common.MatchesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rwc/2023/pools")
public class MatchesController {
    @Autowired
    private MatchesService matchesService;
    @GetMapping("/{pool_id}")
    public ResponseEntity<MatchesDTO> getMatchById(@PathVariable String pool_id) {
        MatchesDTO matchesDTO = matchesService.getMatchById(pool_id);
        return ResponseEntity.ok(matchesDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<MatchesDTO>> getAllFormaciones() {
        List<MatchesDTO> formaciones = matchesService.getAllMatch();
        return ResponseEntity.ok(formaciones);
    }
}
