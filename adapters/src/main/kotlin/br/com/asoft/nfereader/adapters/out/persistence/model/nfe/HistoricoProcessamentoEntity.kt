package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDate

@Entity
@Table(
    name = "historico_processamento",
    indexes = [Index(name = "hist_user_create_index", columnList = "userCreate")]
)
data class HistoricoProcessamentoEntity(
    val nomeArquivo: String,
    val dataProcessamento: LocalDate,
    val processadaCorretamente: Boolean,
    val motivo: String,
    override val userCreate: String,
) : ByUserEntity(userCreate)
