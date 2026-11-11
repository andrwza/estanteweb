<#include "cabecalho.ftlh">

<h2>Corrigir Livro</h2>

<#if erro??>
    <p>${erro}</p>
</#if>

<form method="post" action="/livros/${livro.id?c}">
    <label for="titulo">Título:</label><br>
    <input type="text" id="titulo" name="titulo" value="${livro.titulo!}"><br><br>

    <label for="autor">Autor:</label><br>
    <input type="text" id="autor" name="autor" value="${livro.autor!}"><br><br>

    <label for="ano">Ano:</label><br>
    <input type="text" id="ano" name="ano" value="${livro.ano?c}"><br><br>

    <label for="preco">Preço:</label><br>
    <input type="text" id="preco" name="preco" value="${livro.preco?c}"><br><br>

    <button type="submit">Salvar correção</button>
</form>
