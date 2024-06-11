package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.*

object AnaliseClienteMapper {

    fun AnaliseQualitativaCliente.toDto() =
        AnaliseQualitativaClienteDTO(
            cnpj, razaoSocial, sum, min, max, avg, count, tipoDocumento
        )

    fun DestinatarioCabecalho.toDto() =
        DestinatarioCabecalhoDTO(tipoDocumento, id, nome, cnpj, uf, municipio, telefone, cep)

    fun Destinatario.toDto() =
        DestinatarioDTO(
            id,
            cnpj,
            razaoSocial,
            ie,
            uf,
            municipio,
            bairro,
            telefone,
            cep,
            logradouro,
            numero,
            cPais,
            xPais,
            indIEDest
        )

    fun EstatisticaDestinatario.toDto() =
        EstatisticaDestinatarioDTO(
            razaoSocial,
            cpfCnpj,
            menorCompra,
            maiorCompra,
            totalPedidos,
            valorTotalCompras,
            valorMedioPedido,
            dataUltimaCompra,
            quantidadeMediaItensPorPedido,
            participacaoCliente,
            rankingFaturamento,
            rankingQtdPedidos
        )
}