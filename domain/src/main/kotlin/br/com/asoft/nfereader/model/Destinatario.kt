package br.com.asoft.nfereader.model

data class Destinatario(
    val id: Long,
    val cnpj: String,
    val razaoSocial: String,
    val ie: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val bairro: String? = null,
    val telefone: String? = null,
    val cep: String? = null,
    val logradouro: String? = null,
    val numero: String? = null,
    val cPais: String? = null,
    val xPais: String? = null,
    val indIEDest: String? = null,
)