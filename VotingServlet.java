package com.onlinevoting.servlet;

import com.onlinevoting.dao.VotingDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import com.onlinevoting.model.Candidate;

public class VotingServlet extends HttpServlet {
    private VotingDAO votingDAO = new VotingDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String candidateId = request.getParameter("candidateId");

        boolean isVoted = votingDAO.vote(userId, candidateId);  // Call DAO to insert vote

        if (isVoted) {
            response.sendRedirect("voteSuccess.jsp");  // Redirect to success page
        } else {
            response.sendRedirect("voteError.jsp");  // Redirect to error page
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidate> candidates = votingDAO.getCandidates();
        request.setAttribute("candidates", candidates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
        dispatcher.forward(request, response);
    }
}
