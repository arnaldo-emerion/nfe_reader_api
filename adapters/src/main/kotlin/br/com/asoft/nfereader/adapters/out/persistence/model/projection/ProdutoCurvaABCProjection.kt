package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface ProdutoCurvaABCProjection {
    val id: Int
    val codigo: String
    val descricao: String
    val volume: Double
    val faturamento: Double
    val frequencia: Double
    val precoMedio: Double
    val ticketMedio: Double
    val unidade: String
    val ncm: String
    val tipoDocumento: String
}