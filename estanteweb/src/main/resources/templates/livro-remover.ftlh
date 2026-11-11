<#-- NOVO: arquivo inteiro. Tela de confirmação da remoção. -->
<#include "cabecalho.ftlh">

<h2>Remover Livro</h2>

<p>Você está prestes a remover este livro do acervo:</p>

<p><strong>Título:</strong> ${livro.titulo}</p>
<p><strong>Autor:</strong> ${livro.autor}</p>
<p><strong>Ano:</strong> ${livro.ano?c}</p>
<p><strong>Preço:</strong> R$ ${livro.preco?string["0.00"]}</p>

<#-- O botão "Confirmar remoção" envia um POST (só ele apaga o livro).
     Ele tem aparência de link, mas continua sendo botão de formulário.
     "Desistir" é um link comum que volta para a ficha sem apagar nada. -->
<form method="post" action="/livros/${livro.id?c}/remover">
    <button type="submit" style="background: none; border: none; padding: 0; color: #0000EE; text-decoration: underline; cursor: pointer; font: inherit;">Confirmar remoção</button> |
    <a href="/livros/${livro.id?c}">Desistir</a>
</form>
