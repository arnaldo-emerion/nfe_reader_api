package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.projection.AnalisysTotalProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoListagemNotaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.RankingProjection
import br.com.asoft.nfereader.model.*

object NFeMapper {

    fun ProdutoListagemNotaProjection.toDomain() =
        ProdutoListagemNota(
            id,
            naturezaOperacao,
            numeroNotaFiscal,
            razaoSocial,
            chaveAcesso,
            uf,
            municipio,
            valorNFe,
            emissao,
            cnpjDestinatario,
            valorUnitario,
            quantidade,
            valorTotal,
            tipoDocumento
        )

    fun ProdutoListagemNota.toDto() =
        ProdutoListagemNotaDTO(
            id,
            naturezaOperacao,
            numeroNotaFiscal,
            razaoSocial,
            chaveAcesso,
            uf,
            municipio,
            valorNFe,
            emissao,
            cnpjDestinatario,
            valorUnitario,
            quantidade,
            valorTotal,
            tipoDocumento
        )

    fun RankingProjection.toDto() =
        Ranking(destinatarioId, count)

    fun AnalisysTotalProjection.toModel() =
        AnalisysTotal(
            totalNotasProcessadas,
            totalCupomFiscalProcessados,
            clienteCount,
            produtoCount,
            valorTotalProcessado
        )

    fun AnalisysTotal.toDto() =
        AnalisysTotalDTO(
            totalNotasProcessadas,
            totalCupomFiscalProcessados,
            clienteCount,
            produtoCount,
            valorTotalProcessado
        )
}