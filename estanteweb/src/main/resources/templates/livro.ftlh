<#include "cabecalho.ftlh">

<h2>Ficha do Livro</h2>

<p><strong>Título:</strong> ${livro.titulo}</p>
<p><strong>Autor:</strong> ${livro.autor}</p>
<p><strong>Ano:</strong> ${livro.ano}</p>
<p><strong>Preço:</strong> R$ ${livro.preco?string["0.00"]}</p>

<#-- NOVO: links da ficha para a tela de correção e para a tela de remoção.
     Eles só abrem as telas; nenhum deles grava ou apaga nada. -->
<p>
    <a href="/livros/${livro.id?c}/editar">Editar</a> |
    <a href="/livros/${livro.id?c}/remover">Remover</a>
</p>

<#if outrosLivros?? && (outrosLivros?size > 0)>
    <h3>Outros títulos do mesmo autor:</h3>
    <ul>
        <#list outrosLivros as outro>
            <li>
                <a href="/livros/${outro.id?c}">${outro.titulo}</a> (${outro.ano}) - R$ ${outro.preco?string["0.00"]}
            </li>
        </#list>
    </ul>
</#if>

<p>
    <a href="/livros/html">Voltar para o acervo</a>
</p>
