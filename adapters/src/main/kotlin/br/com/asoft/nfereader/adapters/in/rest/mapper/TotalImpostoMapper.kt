package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.NFeTotalICMS
import br.com.asoft.nfereader.model.NFeTotalICMSDTO

object TotalImpostoMapper {
    fun NFeTotalICMS.toDto() =
        NFeTotalICMSDTO(
            valorNotaFiscal,
            valorBaseCalculo,
            valorIcms,
            valorBaseCalculoST,
            valorST,
            valorProdutos,
            valorFrete,
            valorSeguro,
            valorDesconto,
            valorImpostoImportacao,
            valorIpi,
            valorCofins,
            valorOutros,
            valorIcmsDesonerado,
            valorFcp,
            valorIcmsUfDestinatario,
            valorIcmsUfRemetente,
            valorFcpSTRetido,
            percentualFcpSTRetido,
            valorIpiDevolucao,
            valorTotalTributos,
            valorPis
        )
}