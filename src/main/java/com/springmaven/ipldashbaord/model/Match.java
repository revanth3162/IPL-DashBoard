package com.springmaven.ipldashbaord.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {
    @Id
    private long id;
    
    private int season;
    
    private String description;

    private String team1;
    private String team2;
    private String tossWinner;
    private String matchWinner;
    public String getKeyBatsmen1() {
        return keyBatsmen1;
    }
    public void setKeyBatsmen1(String keyBatsmen1) {
        this.keyBatsmen1 = keyBatsmen1;
    }
    public String getKeyBatsmen2() {
        return keyBatsmen2;
    }
    public void setKeyBatsmen2(String keyBatsmen2) {
        this.keyBatsmen2 = keyBatsmen2;
    }
    public String getKeyBowler1() {
        return keyBowler1;
    }
    public void setKeyBowler1(String keyBowler1) {
        this.keyBowler1 = keyBowler1;
    }
    public String getKeyBowler2() {
        return keyBowler2;
    }
    public void setKeyBowler2(String keyBowler2) {
        this.keyBowler2 = keyBowler2;
    }

    private String tossDecision;
    private String score2;
    private String keyBatsmen1;
    private String keyBatsmen2;
    private String keyBowler1;
    private String keyBowler2;

    public int getSeason() {
        return season;
    }
    public void setSeason(int season) {
        this.season = season;
    }
    public String getMatchWinner() {
        return matchWinner;
    }
    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    
    private String score1;
    
    private String result;
    private String venueName;
    private String pom;
    private String date;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getScore1() {
        return score1;
    }
    public String getScore2() {
        return score2;
    }
    public void setScore2(String score2) {
        this.score2 = score2;
    }
    public void setScore1(String score1) {
        this.score1 = score1;
    }
    public String getTossWinner() {
        return tossWinner;
    }
    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }
    public String getTossDecision() {
        return tossDecision;
    }
    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public String getPom() {
        return pom;
    }
    public void setPom(String pom) {
        this.pom = pom;
    }
    public String getTeam1() {
        return team1;
    }
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    
    public String getTeam2() {
        return team2;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
