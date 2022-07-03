package com.springmaven.ipldashbaord.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springmaven.ipldashbaord.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{

    List<Match> getByTeam1AndSeasonIsOrTeam2AndSeasonIsOrderByDateDesc(String teamName1, int season1, String teamName2, int season2);
    List<Match> getTop4ByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2);
}
