package br.com.asoft.nfereader.model

data class ProdutoCurvaABC(
    val id: Int,
    val codigo: String,
    val descricao: String,
    val volume: Double,
    val faturamento: Double,
    val frequencia: Double,
    val precoMedio: Double,
    val ticketMedio: Double,
    val unidade: String,
    val ncm: String,
    val tipoDocumento: String,
    var partVolume: Double? = null,
    var partFaturamento: Double? = null,
    var partFrequencia: Double? = null,
)
