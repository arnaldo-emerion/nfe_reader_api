package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.BasicAXChartInfo
import br.com.asoft.nfereader.model.BasicValueTotalDTO
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.PedidosDiaADiaDTO

object CurvaABCMapper {
    fun BasicAXChartInfo.toDto(): BasicValueTotalDTO {
        return BasicValueTotalDTO(
            label = this.name,
            total = this.total
        )
    }

    fun PedidosDiaADia.toDto() =
        PedidosDiaADiaDTO(dataEmissao, total)
}