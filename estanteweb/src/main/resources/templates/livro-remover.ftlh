<#include "cabecalho.ftlh">

<h2>Remover Livro</h2>

<p>Você está prestes a remover este livro do acervo:</p>

<p><strong>Título:</strong> ${livro.titulo}</p>
<p><strong>Autor:</strong> ${livro.autor}</p>
<p><strong>Ano:</strong> ${livro.ano?c}</p>
<p><strong>Preço:</strong> R$ ${livro.preco?string["0.00"]}</p>

<form method="post" action="/livros/${livro.id?c}/remover">
    <button type="submit" style="background: none; border: none; padding: 0; color: #0000EE; text-decoration: underline; cursor: pointer; font: inherit;">Confirmar remoção</button> |
    <a href="/livros/${livro.id?c}">Desistir</a>
</form>
