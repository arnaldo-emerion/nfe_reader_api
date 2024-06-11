package br.com.asoft.nfereader.model

data class Transportadora(
    val id: Long,
    val cnpj: String,
    val razaoSocial: String,
    val ie: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val endereco: String? = null,
    val userCreate: String
)
