<%--
  Created by IntelliJ IDEA.
  User: Bahenduzi
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
<img src="Assets/bg1.png" id="bg" alt="">

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
<div class="mx-auto order-0">
    <a class="navbar-brand mx-auto d-flex justify-content-center" href="#">ChatApp</a>
</div>
</nav>
<div id="jumbo" class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 id="name" class="display-4 text-center">Welcome to ChatApp!</h1>
        <p class="lead text-center">A chatroom for everyone.</p>
        <hr class="my-4">
        <dl>
            <dt>Enter your name</dt>
        </dl>
        <div class="chatActions">
            <form class="messageSubmit" action="ChatServlet" method="POST"></form>
            <section id="forum-posts">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">@</span>
                    </div>
                    <input type="text" class="form-control" name="username" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                </div>
            </section>
        </div>
        <div id="btn" class="container text-center">
            <a class="btn btn-outline-dark btn-lg" href="Chatapp.html" role="button">Join the chat</a>
        </div>
    </div>
</div>
</body>
</html>
