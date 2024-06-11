package br.com.asoft.nfereader.model

data class AnaliseQualitativaCliente(
    val cnpj: String,
    val razaoSocial: String,
    val sum: Double,
    val min: Double,
    val max: Double,
    val avg: Double,
    val count: Double,
    val tipoDocumento: String
)