package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface AnaliseQualitativaClienteProjection {
    val cnpj: String
    val razaoSocial: String
    val sum: Double
    val min: Double
    val max: Double
    val avg: Double
    val count: Double
    val tipoDocumento: String
}