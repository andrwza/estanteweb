<#include "cabecalho.ftlh">

<form method="POST" action="/reserva">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="${nome!}"><br><br>

    <label for="tel">Telefone:</label>
    <input type="tel" id="tel" name="telefone" value="${tel!}"><br><br>

    <#if erro??>
        <p>${erro}</p>
    </#if>

    <input type="submit" value="Reservar">

</form>