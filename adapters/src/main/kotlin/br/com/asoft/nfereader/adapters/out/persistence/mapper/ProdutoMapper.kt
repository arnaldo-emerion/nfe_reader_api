package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.ProdutoEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoCabecalhoProjection
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.ProdutoCurvaABCProjection
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC

object ProdutoMapper {

    fun ProdutoCurvaABCProjection.toDomain() =
        ProdutoCurvaABC(
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
            tipoDocumento
        )

    fun ProdutoCabecalhoProjection.toDomain() =
        Produto(tipoDocumento, codigo, descricao, ean, unidade, ncm)

}