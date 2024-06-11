package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface RankingProjection {
    val destinatarioId: Long
    val count: Int
}