package br.com.asoft.nfereader.model

data class ParametroNatOperacao(
    val id: Long,
    val nfeProcessaveisList: List<String>,
    val name: String,
    val active: Boolean
)
