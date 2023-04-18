<%-- 
    Document   : profile
    Created on : 04 14, 23, 6:10:35 PM
    Author     : levinjacob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="img/jpeg; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="profilestylestest.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kumbh+Sans:wght@700&family=Lexend+Deca&display=swap" rel="stylesheet">
        <title>Profile Page</title>
    </head>
    <body>
         <header>
            <nav class="navbar"> 
                <header>
                    <h1>Covid-19 Tracker</h1>
                </header>
                <ul name="navigation" class="navlist">
                    <li><a href="${pageContext.request.contextPath}/homepage.jsp" class="navlink">Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/menu.jsp" class="navlink">Menu</a></li>
                    <li><a href="${pageContext.request.contextPath}/AboutServlet" class="navlink">About</a></li>
                </ul> 
            </nav>
            </header>
            <main>
                <div class="profilepic">
                    <img src="https://www.w3schools.com/howto/img_avatar.png" alt="profile picture">
                </div>
                <div class="profileinfo">
                    <h1>Profile Information</h1>
                    <p>First Name: ${requestScope.user.firstName}</p> 
                    <p>Last Name: ${requestScope.user.lastName}</p>
                    <p>Email: ${requestScope.user.email}</p>
                    <p>Phone Number: ${requestScope.user.phoneNumber}</p>
                    <p>Address: ${requestScope.user.address}</p>
                    <p>Birthday: </P>
                    <p>Gender: </P>
                    <p>Status: </P>
                </div>
                <h1 class="bg-red-200">Subdivision COVID-19 Tracker</h1>
            </main>
    </body>
</html>
