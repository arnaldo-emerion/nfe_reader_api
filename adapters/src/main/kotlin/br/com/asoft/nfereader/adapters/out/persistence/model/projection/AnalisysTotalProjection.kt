package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface AnalisysTotalProjection {
    val totalNotasProcessadas: Double
    val totalCupomFiscalProcessados: Double
    val clienteCount: Double
    val produtoCount: Double
    val valorTotalProcessado: Double
}