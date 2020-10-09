<%--
  Created by IntelliJ IDEA.
  User: alway
  Date: 2020-10-01
  Time: 3:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="Chatapp.css">
    <script src="https://kit.fontawesome.com/be7fa3ac62.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Update</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Set refresh Timer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Top</a>
            </li>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="#">The Forum</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span class="navbar-toggler-icon"></span>
        </button>
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
                    Dropdown link
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Clear Chat</a>
                    <a class="dropdown-item" href="#">Download chat</a>
                    <a class="dropdown-item" href="#">Go to servlet</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Login/Signup</a>
            </li>
        </ul>
    </div>
</nav>

<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Welcome to the Forum!</h1>
        <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
        <dl>
            <dt>Definition list</dt>
            <dd>Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                commodo consequat.</dd>
            <dt>Lorem ipsum dolor sit amet</dt>
            <dd>Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                commodo consequat.</dd>
        </dl>
    </div>
</div>

<jsp:useBean id="chat" scope="session" class="beans.ChatBean" />
    <%-- THis compiles fine --%>
<jsp:setProperty name="chat" property="msg" value="<%= request.getAttribute(\"out\").toString() %>" />


<section id="forum-thread">
    <div class="card">
        <h4>Person 1 posted:</h4>
        <div class="card-body">
            ${chat.printDb()}
        </div>
        <hr>
    </div>

    <!--    <div class="card">-->
    <!--        <h4>Person 1 posted:</h4>-->
    <!--        <div class="card-body">-->
    <!--            This is some text within a card body.-->
    <!--        </div>-->
    <!--        <hr>-->
    <!--    </div>-->

    <div class="chatActions">
        <h4>person 2</h4>
        <form class="messageSubmit" action="ChatServlet" method="POST">
            <label for="messageBox"></label>
            <textarea id="messageBox" name="message" placeholder="Type your message..." rows="4" cols="50"></textarea>
            <input class="btn" type="submit" value="Submit"> <br><br>
            <input class="btn" type="submit" value="Clear">
            <input class="btn" type="submit" value="Download">
        </form>
    </div>
</section>
<hr>
<section id="forum-posts">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">@</span>
        </div>
        <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
    </div>

    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
        <div class="input-group-append">
            <span class="input-group-text" id="basic-addon2">@example.com</span>
        </div>
    </div>

    <label for="basic-url">Your vanity URL</label>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
        </div>
        <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">
    </div>

    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">$</span>
        </div>
        <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
        <div class="input-group-append">
            <span class="input-group-text">.00</span>
        </div>
    </div>

    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text">With textarea</span>
        </div>
        <textarea class="form-control" aria-label="With textarea"></textarea>
    </div>
    <div> Click here to go to <a href="ChatServlet">servlet</a></div>
</section>

<script>
    const checkbox = document.getElementById('checkbox');
    checkbox.addEventListener('change',() => {
        //    change theme
        document.body.classList.toggle('dark');
    });
</script>


<!--    <div>Welcome to the Chat room!</div>-->
<!---->
<!--    <div class="homepage">-->

<!--        <div class="chat">This is where all the messages from users will be displayed-->
<!--            <div class="message" id="user1">-->
<!--                <span class="messageText">hello this is a test</span>-->
<!--            </div>-->

<!--            <div class="message" id="user2">-->
<!--                <span class="messageText">hello this is a test</span>-->
<!--            </div>-->

<!--        </div>-->

<!--        <div class="chatActions">-->
<!--            <form class="messageSubmit" action="">-->
<!--                <label for="messageBox"></label>-->
<!--                <textarea id="messageBox" name="message" placeholder="Type your message..." rows="4" cols="50"></textarea>-->
<!--                <input class="btn" type="submit" value="Submit"> <br><br>-->
<!--                <input class="btn" type="submit" value="Clear">-->
<!--                <input class="btn" type="submit" value="Download">-->
<!--            </form>-->
<!--        </div>-->

<!--    </div>-->
</body>
</html>
