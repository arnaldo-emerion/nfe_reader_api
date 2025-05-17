package br.com.asoft.nfereader.model

import java.time.LocalDate

data class HistoricoProcessamento(
    val nomeArquivo: String,
    val dataProcessamento: LocalDate,
    val processadaCorretamente: Boolean,
    val motivo: String
)
