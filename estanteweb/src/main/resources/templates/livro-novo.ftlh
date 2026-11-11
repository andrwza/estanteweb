<#include "cabecalho.ftlh">
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Livro</title>
</head>
<body>
    <h1>Novo Livro</h1>

    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>

    <form method="post" action="/livros">
        <label for="titulo">Título:</label><br>
        <input type="text" id="titulo" name="titulo" value="${titulo!}"><br><br>

        <label for="autor">Autor:</label><br>
        <input type="text" id="autor" name="autor" value="${autor!}"><br><br>

        <label for="ano">Ano:</label><br>
        <input type="text" id="ano" name="ano" value="${(ano?c)!}"><br><br>

        <label for="preco">Preço:</label><br>
        <input type="text" id="preco" name="preco" value="${(preco?c)!}"><br><br>

        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>
