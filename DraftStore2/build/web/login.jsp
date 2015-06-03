<%-- 
    Document   : login
    Created on : 08/05/2015, 00:16:55
    Author     : marcos.agomes
--%>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>
            Draftstore - Login
        </title>
        <link rel="stylesheet" href="../DraftStore/resources/login/css/style.css">
        <link rel="icon" href="../DraftStore/resources/img/draft.ico" type="image/x-icon">
        <link rel="shortcut icon" href="../DraftStore/resources/img/draft.ico" type="image/x-icon">
        <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    </head>
    <body>
        <div class="logo_login">
        </div>
        <form action="Login" 
              method="post" 
              accept-charset="UTF-8" 
              enctype="application/x-www-form-urlencoded"
              class="login">
            <p>
                <label for="login">Login </label>
                <input type="text" name="login" id="login" placeholder="usuario@draftstore">
            </p>

            <p>
                <label for="password">Senha</label>
                <input type="password" name="password" id="password" placeholder="*********">
            </p>

            <p class="login-submit">
                <button type="submit" class="login-button">Login</button>
            </p>

            <p class="forgot-password"><a href="#">Esqueceu sua senha?<br>Contate a equipe de suporte.</a></p>
        </form>

        <section class="about">
            <p class="about-links">

                Copyright © 2015 WRM Sistemas
            </p>

        </section>
    </body>
</html>