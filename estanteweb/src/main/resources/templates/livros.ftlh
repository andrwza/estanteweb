<#include "cabecalho.ftlh">

<h1>Lista de Livros</h1>

<p>
    Ordenar por:
    <a href="/livros/html?ordem=titulo">Título</a> |
    <a href="/livros/html?ordem=autor">Autor</a> |
    <a href="/livros/html?ordem=ano">Ano</a> |
    <a href="/livros/html?ordem=preco">Preço</a> |
    <a href="/livros/html">Padrão</a>
</p>

<ul>
    <#if livros?size == 0>
        <strong>Não há nenhum livro!</strong>
    <#else>
        <#list livros as livro>
            <li><strong>Livro:</strong> <a href="/livros/${livro.id?c}">${livro.titulo}</a></li>
            <li>Autor: ${livro.autor}</li>
            <li>Preço: R$ ${livro.preco?string["0.00"]}</li>
            <#-- NOVO: links para corrigir ou remover cada livro direto da listagem -->
            <li><a href="/livros/${livro.id?c}/editar">Editar</a> | <a href="/livros/${livro.id?c}/remover">Remover</a></li>
            <br>
        </#list>
    </#if>
</ul>

<p>
    <a href="/livros/novo">Cadastrar novo livro</a>
</p>
