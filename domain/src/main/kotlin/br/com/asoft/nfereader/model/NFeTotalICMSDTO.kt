package br.com.asoft.nfereader.model

data class NFeTotalICMS(
    val percentualFcpSTRetido: Double,
    val valorBaseCalculo: Double,
    val valorBaseCalculoST: Double,
    val valorCofins: Double,
    val valorDesconto: Double,
    val valorFcp: Double,
    val valorFcpSTRetido: Double,
    val valorFrete: Double,
    val valorIcms: Double,
    val valorIcmsDesonerado: Double,
    val valorIcmsUfDestinatario: Double,
    val valorIcmsUfRemetente: Double,
    val valorImpostoImportacao: Double,
    val valorIpi: Double,
    val valorIpiDevolucao: Double,
    val valorNotaFiscal: Double,
    val valorOutros: Double,
    val valorProdutos: Double,
    val valorPis: Double,
    val valorST: Double,
    val valorSeguro: Double,
    val valorTotalTributos: Double
)