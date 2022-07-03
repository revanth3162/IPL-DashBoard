import { React } from 'react';
import { Link } from 'react-router-dom';
import "./MatchDetailCard.scss"


export const MatchDetailCard = ({teamName, match}) => {
  if(!match) return null;
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/team/${otherTeam}`;
  const isMatchWon = teamName === match.matchWinner;
  return (
    <div className={isMatchWon ? 'MatchDetailCard won-card':'MatchDetailCard lost-card'}>
      <span className="vs">vs </span> 
      <h1>
        <Link to={otherTeamRoute}>{otherTeam}</Link>
      </h1> 
      <p className="match-details">{match.description}</p>
      <p> Stadium: {match.venueName}</p>

      <div className="match-toss-winner">
      <p>{match.tossWinner} won the toss and decided to {match.tossDecision}</p>
      </div>

      <div className="match-statistics">
      <div>
      <p> {match.team1}</p>
      <p> {match.score1}</p>
      <p> {match.keyBatsmen1}</p>
      <p> {match.keyBowler1}</p>
      <p> {match.keyPlayers1}</p>
      </div>
      <div>
      <p> {match.team2}</p>
      <p> {match.score2}</p>
      <p> {match.keyBatsmen2}</p>
      <p> {match.keyBowler2}</p>
      <p> {match.keyPlayers2}</p>
      </div>
      </div>
      
      
      <div className="match-result-section">
      <p>{match.result}</p>
      <p> {match.pom} is awarded with Man Of the Match</p>
      </div>
      
      

    </div>
  );
}

export default MatchDetailCard;