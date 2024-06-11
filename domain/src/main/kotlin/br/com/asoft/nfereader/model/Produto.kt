package br.com.asoft.nfereader.model

data class Produto(
    val tipoDocumento: String,
    val codigo: String,
    val descricao: String,
    val ean: String,
    val unidade: String,
    val ncm: String
)
