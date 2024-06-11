package br.com.asoft.nfereader.model

data class Emitente(
    val id: Long,
    val cnpj: String,
    val razaoSocial: String,
    val nomeFantasia: String? = null,
    val ie: String? = null,
    val crt: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val bairro: String? = null,
    val telefone: String? = null,
    val logradouro: String? = null,
    val cPais: String? = null,
    val xPais: String? = null,
    val cep: String? = null,
    val userCreate: String,
)
