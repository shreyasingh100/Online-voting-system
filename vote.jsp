<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vote</title>
</head>
<body>
    <h2>Choose Your Candidate</h2>
    <form action="VotingServlet" method="POST">
        <select name="candidateId" required>
            <c:forEach var="candidate" items="${candidates}">
                <option value="${candidate.candidateId}">${candidate.name} - ${candidate.party}</option>
            </c:forEach>
        </select>
        <button type="submit">Submit Vote</button>
    </form>
</body>
</html>
