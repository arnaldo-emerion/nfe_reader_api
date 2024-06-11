package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface DestinatarioCabecalhoProjection {
    val tipoDocumento: String
    val id: Long
    val nome: String
    val cnpj: String
    val uf: String
    val municipio: String
    val telefone: String
    val cep: String
}