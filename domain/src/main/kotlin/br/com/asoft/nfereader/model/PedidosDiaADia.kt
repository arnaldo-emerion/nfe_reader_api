package br.com.asoft.nfereader.model

import java.time.LocalDate

data class PedidosDiaADia(
    val dataEmissao: LocalDate,
    val total: Double
)
