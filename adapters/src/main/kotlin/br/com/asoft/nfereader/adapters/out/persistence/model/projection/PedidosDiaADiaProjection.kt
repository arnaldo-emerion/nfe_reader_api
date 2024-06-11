package br.com.asoft.nfereader.adapters.out.persistence.model.projection

import java.time.LocalDate

interface PedidosDiaADiaProjection {
    val dataEmissao: LocalDate
    val total: Double
}