<#include "cabecalho.ftlh">
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Contato</title>
</head>
<body>
    <h1>Fale conosco</h1>
    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>
    <form method="post" action="/contato">
        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="${nome!}"><br><br>

        <label for="mensagem">Mensagem:</label><br>
        <textarea id="mensagem" name="mensagem" rows="5" cols="30"></textarea><br><br>

        <button type="submit">Enviar</button>
    </form>
</body>
</html>
