package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoListagemNotaProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.RankingProjection
import br.com.asoft.nfereader.model.ProdutoListagemNota
import br.com.asoft.nfereader.model.ProdutoListagemNotaDTO
import br.com.asoft.nfereader.model.Ranking

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
}