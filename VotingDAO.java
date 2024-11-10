package com.onlinevoting.dao;

import com.onlinevoting.util.DBUtil;
import java.sql.*;
import com.onlinevoting.model.User;
import com.onlinevoting.model.Candidate;

public class VotingDAO {

    public boolean validateUser(String username, String password) {
        try (Connection connection = DBUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                return rs.next();  // returns true if user exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean vote(String userId, String candidateId) {
        try (Connection connection = DBUtil.getConnection()) {
            String query = "INSERT INTO votes (user_id, candidate_id) VALUES (?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, userId);
                pst.setString(2, candidateId);
                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;  // Vote successfully inserted
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Candidate> getCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection()) {
            String query = "SELECT * FROM candidates";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Candidate candidate = new Candidate();
                    candidate.setCandidateId(rs.getInt("candidate_id"));
                    candidate.setName(rs.getString("name"));
                    candidate.setParty(rs.getString("party"));
                    candidates.add(candidate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }
}
