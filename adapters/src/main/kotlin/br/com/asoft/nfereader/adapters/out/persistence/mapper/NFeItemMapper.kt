package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.projection.NFeItemProjection
import br.com.asoft.nfereader.model.NFeItemDTO
import br.com.asoft.nfereader.model.NFeItemModel

object NFeItemMapper {

    fun NFeItemProjection.toDomain() =
        NFeItemModel(
            codigo = codigo,
            descricao = descricao,
            quantidade = quantidade,
            unidade = unidade,
            valorUnitario = valorUnitario,
            valorProduto = valorProduto,
            ncm = ncm,
            cfop = cfop,
            nItem = nItem
        )

    fun NFeItemModel.toDTO() =
        NFeItemDTO(
            codigo = codigo,
            descricao = descricao,
            quantidade = quantidade,
            unidade = unidade,
            valorUnitario = valorUnitario,
            valorProduto = valorProduto,
            ncm = ncm,
            cfop = cfop,
            nItem = nItem
        )
}