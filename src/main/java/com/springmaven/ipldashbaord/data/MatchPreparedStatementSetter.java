package com.springmaven.ipldashbaord.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.springmaven.ipldashbaord.model.Match;

public final class MatchPreparedStatementSetter implements ItemPreparedStatementSetter<Match>{

    @Override
    public void setValues(Match match, PreparedStatement ps) throws SQLException {
        ps.setString(1, Integer.toString(match.getSeason()));
        ps.setString(2, Long.toString(match.getId()));
        ps.setString(3, match.getDescription());
        ps.setString(4, match.getTeam1());
        ps.setString(5, match.getTeam2());
        ps.setString(6, match.getTossWinner());
        ps.setString(7, match.getTossDecision());
        ps.setString(8, match.getScore1());
        ps.setString(9, match.getScore2());
        ps.setString(10, match.getKeyBatsmen1());
        ps.setString(11, match.getKeyBatsmen2());
        ps.setString(12, match.getKeyBowler1());
        ps.setString(13, match.getKeyBowler2());
        ps.setString(14, match.getResult());
        ps.setString(15, match.getVenueName());
        ps.setString(16, match.getPom());
        ps.setString(17, match.getDate());
        ps.setString(18, match.getMatchWinner());
    }
}
