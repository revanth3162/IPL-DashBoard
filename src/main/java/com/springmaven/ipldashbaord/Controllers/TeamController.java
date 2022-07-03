package com.springmaven.ipldashbaord.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springmaven.ipldashbaord.model.Match;
import com.springmaven.ipldashbaord.model.Team;
import com.springmaven.ipldashbaord.repository.MatchRepository;
import com.springmaven.ipldashbaord.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.getTop4ByTeam1OrTeam2OrderByDateDesc(teamName, teamName));
        return team;
    }
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam String year){
        return this.matchRepository.getByTeam1AndSeasonIsOrTeam2AndSeasonIsOrderByDateDesc(teamName,Integer.valueOf(year), teamName, Integer.valueOf(year));
    }
    @GetMapping("/team")
    public Iterable<Team> getTeamList(){
        return this.teamRepository.findAll();
    }
}
