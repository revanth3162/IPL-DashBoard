package com.springmaven.ipldashbaord.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springmaven.ipldashbaord.model.Match;
import com.springmaven.ipldashbaord.model.Team;
import com.springmaven.ipldashbaord.repository.MatchRepository;
import com.springmaven.ipldashbaord.repository.TeamRepository;

import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/ipl")
public class TeamController {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team")
    public Iterable<Team> getTeamList(){
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@ApiParam(value="Short Name of team", required=true)@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.getTop4ByTeam1OrTeam2OrderByDateDesc(teamName, teamName));
        return team;
    }
    

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam String year){
        return this.matchRepository.getByTeam1AndSeasonIsOrTeam2AndSeasonIsOrderByDateDesc(teamName,Integer.valueOf(year), teamName, Integer.valueOf(year));
    }
}
