package br.com.asoft.nfereader.model

data class DestinatarioCabecalho(
    val tipoDocumento: String,
    val id: Long,
    val nome: String,
    val cnpj: String,
    val uf: String,
    val municipio: String,
    val telefone: String,
    val cep: String
)