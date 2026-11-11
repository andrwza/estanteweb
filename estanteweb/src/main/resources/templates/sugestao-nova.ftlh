<#include "cabecalho.ftlh">
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Nova Sugestão</title>
</head>
<body>
    <h1>Nova Sugestão</h1>
    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>
    <form action="/sugestoes" method="POST">
        <label for="nomeCliente">Nome do Cliente:</label>
        <input type="text" id="nomeCliente" name="nomeCliente" value="${nomeCliente!}"><br>
        <label for="livroSugerido">Livro Sugerido:</label>
        <input type="text" id="livroSugerido" name="livroSugerido" value="${livroSugerido!}"><br>
        <input type="submit" value="Enviar Sugestão">
    </form>
</body>
</html>
