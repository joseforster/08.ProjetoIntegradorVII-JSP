<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Login</title>
    </head>
    <body id="body-login">
        <div class="div-login" id="tela-login">
            <h1>LOGIN</h1>
            <form method="post" action="/ProjetoIntegradorVII/autenticacao">
                <input class="botao-login" name="usuario" placeholder="Seu usuário..." type="text">
                <br/>
                <input class="botao-login" name="senha" placeholder="Sua senha..." type="password">
                <br/>
                <input id="btn-login" type="submit" value="Entrar">
            </form
        </div>
    </body>
</html>