package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface UserStatisticProjection {
    val nfe: Long
    val cupomFiscal: Long
    val qtdMaxNfe: Long?
}