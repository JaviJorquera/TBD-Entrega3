package com.example.restservice.services;

import com.example.restservice.models.Ranking;
import com.example.restservice.repositories.RankingRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "rankings")
public class RankingService {

    private final RankingRepository rankingRepository;

    RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Ranking> getAllRanking() {
        return rankingRepository.getAllRanking();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countRanking() {
        int total = rankingRepository.countRanking();
        return String.format("Tienes %s rankings!!", total);
    }

    @RequestMapping(value = "/getById/{id_Ranking}", method = RequestMethod.GET)
    @ResponseBody
    public Ranking getRankingById(@PathVariable(value = "id_Ranking") Integer id) {
        return this.rankingRepository.getRankingById(id);
    }

    @PostMapping("/newRanking")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking) {
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }

    @PutMapping("/update/{id_Ranking}")
    @ResponseBody
    public void updateRanking(@PathVariable(value = "id_Ranking") int id, Ranking ranking) {
        rankingRepository.updateRanking(id, ranking);
    }

    @PutMapping("/delete/{id_Ranking}")
    @ResponseBody
    public void deleteRanking(@PathVariable(value = "id_Ranking") int id, Ranking ranking){
        rankingRepository.deleteRanking(id, ranking);
    }
}