<#include "cabecalho.ftlh">

<h1>Sugestões</h1>
<#if enviada??>
    <p style="color: green;">Sugestão enviada com sucesso!</p>
</#if>
<ul>
    <#if sugestoes?size == 0>
        <strong>Não há nenhuma sugestão!</strong>
    <#else>
        <#list sugestoes as sugestao>
            <li>Sugestão: ${sugestao.nomeCliente}</li>
            <li>Autor: ${sugestao.livroSugerido}</li>
            <br>
        </#list>
    </#if>
</ul>