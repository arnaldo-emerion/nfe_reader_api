package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface TotalRecordsProjection {
    val nfeCount: Long
    val cupomCount: Long
}