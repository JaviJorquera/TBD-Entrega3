package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Ranking;

public interface RankingRepository {
    public int countRanking();

    public List<Ranking> getAllRanking();

    public Ranking getRankingById(Integer id);

    public Ranking createRanking(Ranking ranking);

    public void updateRanking(int id_Ranking, Ranking ranking);

    public void deleteRanking(int id_Ranking, Ranking ranking);
}