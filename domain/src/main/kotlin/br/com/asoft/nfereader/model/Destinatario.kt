package br.com.asoft.nfereader.model

data class Destinatario(
    val id: Long,
    val cpfCnpj: String,
    val razaoSocial: String,
    val ie: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val bairro: String? = null,
    val telefone: String? = null,
    val cep: String? = null,
    val logradouro: String? = null,
    val numero: String? = null,
    val cPais: Long? = null,
    val xPais: String? = null,
    val indIEDest: Int? = null,
)