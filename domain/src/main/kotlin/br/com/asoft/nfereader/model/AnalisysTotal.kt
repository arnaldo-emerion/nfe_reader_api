package br.com.asoft.nfereader.model

data class AnalisysTotal(
    val totalNotasProcessadas: Double,
    val totalCupomFiscalProcessados: Double,
    val clienteCount: Double,
    val produtoCount: Double,
    val valorTotalProcessado: Double
)