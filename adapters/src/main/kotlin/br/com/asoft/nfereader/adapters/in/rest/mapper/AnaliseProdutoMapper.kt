package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.*

object AnaliseProdutoMapper {
    fun ProdutoCurvaABC.toDto() =
        ProdutoCurvaABCDTO(
            id,
            codigo,
            descricao,
            volume,
            faturamento,
            frequencia,
            precoMedio,
            ticketMedio,
            unidade,
            ncm,
            tipoDocumento,
            partVolume,
            partFaturamento,
            partFrequencia
        )

    fun Produto.toDto() =
        ProdutoDTO(tipoDocumento, codigo, descricao, ean, unidade, ncm)
}