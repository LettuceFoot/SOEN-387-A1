<%--
  Created by IntelliJ IDEA.
  Date: 2020-10-11
  Time: 5:03 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChatApp</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="Chatapp.css">
    <script src="https://kit.fontawesome.com/be7fa3ac62.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>

<%--Left this piece of code just to keep the alignment of the navbar title--%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
            </li>
            <li class="nav-item">
            </li>
            <li class="nav-item">
            </li>
        </ul>
    </div>
    <div id="navbarTitle" class="mx-auto order-0 d-flex justify-content-center">
        <a class="navbar-brand mx-auto " href="welcome.jsp">ChatApp</a>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="switcher">
                <input type="checkbox" class="checkbox" id="checkbox">
                <label for="checkbox" class="label">
                    <i class="fas fa-moon"></i>
                    <i class="fas fa-sun"></i>
                    <i class ="ball"></i>
                </label>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Settings
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Download chat</a>
                    <a class="dropdown-item" href="#">Update chat</a>
                    <a class="dropdown-item" href="#">Clear chat</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="welcome.jsp">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<jsp:useBean id="chat" scope="session" class="beans.ChatBean" />
<%-- THis compiles fine --%>
<jsp:setProperty name="chat" property="date" value="<%= request.getAttribute(\"date\").toString() %>" />
<% if (request.getAttribute("username") != null) { %> <jsp:setProperty name="chat" property="username" value="<%= request.getAttribute(\"username\").toString() %>" /> <% }%>
<% if (request.getAttribute("msg") != null) { %> <jsp:setProperty name="chat" property="msg" value="<%= request.getAttribute(\"msg\").toString() %>" /> <% }%>
<% if (request.getAttribute("clear") != null) { %> <jsp:setProperty name="chat" property="clear" value="<%= request.getAttribute(\"clear\").toString() %>" /> <% }%>
<div id="divReverse">
<section id="forum-thread">
    <div id="firstCont" class="divContainer jumbotron jumbotron-fluid">
        <div class="container">
            ${chat.printDb()}
            <hr>

        </div>
    </div>

    <div class="chatActions">
        <div id="secondCont" class="divContainer jumbotron jumbotron-fluid">
            <div class="container">
                <form class="messageSubmit" action="ChatServlet" method="POST">
                    <label for="messageBox"></label>
                    <div class="chatButtons d-flex justify-content-center">
                        <div>
                            <textarea id="messageBox" name="message" placeholder="Type your message..." rows="2" cols="120"></textarea>
                        </div>
                        <div>
                            <input id="submitBtn" class="chatButtons btn btn-outline-dark btn-lg" type="submit" name="chatAction" value="Submit">
                        </div>
                    </div>
                    <br><br>
                    <div class="d-flex justify-content-center">
                        <div class="nav-item dropdown">
                            <a class="btn nav-link dropdown-toggle" href="#" id="downloadLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Download Chat
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="#">Text file</a>
                                <a class="dropdown-item" href="#">XML file</a>
                            </div>
                        </div>
                        <input id="clearBtn" class="chatButtons btn btn-outline-dark btn-lg" type="submit" name="chatAction" value="Clear Chat">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</div>
<hr>
<script>
    const checkbox = document.getElementById('checkbox');
    checkbox.addEventListener('change',() => {
        //    change theme
        document.body.classList.toggle('dark');
        document.getElementById("submitBtn").classList.toggle("dark");
        document.getElementById("clearBtn").classList.toggle("dark");
        document.getElementById("chatBox").classList.toggle("darkBox");
        document.getElementById("messageBox").classList.toggle("darkBox");
        document.getElementById("downloadLink").classList.toggle("darkLink");
        document.getElementById("firstCont").classList.toggle("firstContPos");
        document.getElementById("secondCont").classList.toggle("secondContPos");
        document.getElementById("divReverse").classList.toggle("divLefty");
    });
</script>
</body>
</html>