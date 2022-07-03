package com.springmaven.ipldashbaord.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.batch.item.ItemProcessor;
import com.springmaven.ipldashbaord.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    Match matchOutput = new Match();
    Integer intObj = Integer.valueOf(matchInput.getSeason());
    matchOutput.setSeason(intObj);
    Long longObj = Long.valueOf(matchInput.getId());
    matchOutput.setId(longObj);
    matchOutput.setDescription(matchInput.getDescription());
    String firstInningsTeam, secondInningsTeam;
    if("BAT FIRST".equals(matchInput.getDecision())){
      firstInningsTeam = matchInput.getToss_won();
      secondInningsTeam = matchInput.getToss_won().equals(matchInput.getHome_team()) 
        ? matchInput.getAway_team() : matchInput.getHome_team();
    }
    else if("BOWL FIRST".equals(matchInput.getDecision())){
      secondInningsTeam = matchInput.getToss_won();
      firstInningsTeam =  matchInput.getToss_won().equals(matchInput.getHome_team()) 
      ? matchInput.getAway_team() : matchInput.getHome_team();
    }
    else{
      firstInningsTeam = matchInput.getHome_team();
      secondInningsTeam = matchInput.getAway_team();
    }
    String score1, score2, keybatsmen1, keybatsmen2, keybowler2, keybowler1;

    if(firstInningsTeam.equals(matchInput.getHome_team())){
      score1 = matchInput.getHome_runs() +"/"+ matchInput.getHome_wickets() + " in "+ matchInput.getHome_overs()+ " overs";
      score2 = matchInput.getAway_runs() +"/"+ matchInput.getAway_wickets() + " in "+ matchInput.getAway_overs()+ " overs";
      keybowler1 =  "Key Bowler: " + matchInput.getHome_key_bowler();
      keybowler2 = "Key Bowler: " + matchInput.getAway_key_bowler();
      keybatsmen1 = "Key Batsmen: "+ matchInput.getHome_key_batsman();
      keybatsmen2 = "Key Batsmen: "+ matchInput.getAway_key_batsman();
    }
    else{
      score2 = matchInput.getHome_runs() +"/"+ matchInput.getHome_wickets() + " in "+ matchInput.getHome_overs()+ " overs";
      score1 = matchInput.getAway_runs() +"/"+ matchInput.getAway_wickets() + " in "+ matchInput.getAway_overs()+ " overs";
      keybowler2 =  "Key Bowler: " + matchInput.getHome_key_bowler();
      keybowler1 = "Key Bowler: " + matchInput.getAway_key_bowler();
      keybatsmen2 = "Key Batsmen: "+ matchInput.getHome_key_batsman();
      keybatsmen1 = "Key Batsmen: "+ matchInput.getAway_key_batsman();
    }
      
    matchOutput.setTeam1(firstInningsTeam);
    matchOutput.setTeam2(secondInningsTeam);
    matchOutput.setTossWinner(matchInput.getToss_won());
    matchOutput.setTossDecision(matchInput.getDecision());
    matchOutput.setScore1(score1);
    matchOutput.setScore2(score2);
    matchOutput.setResult(matchInput.getResult());
    matchOutput.setVenueName(matchInput.getVenue_name());
    matchOutput.setPom(matchInput.getPom());
    matchOutput.setKeyBatsmen1(keybatsmen1);
    matchOutput.setKeyBatsmen2(keybatsmen2);
    matchOutput.setKeyBowler1(keybowler1);
    matchOutput.setKeyBowler2(keybowler2);
    matchOutput.setMatchWinner(matchInput.getWinner());
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH);
    LocalDate date = LocalDate.parse(matchInput.getStart_date(), inputFormatter);
    matchOutput.setDate(date.toString());
    return matchOutput;

  }

}