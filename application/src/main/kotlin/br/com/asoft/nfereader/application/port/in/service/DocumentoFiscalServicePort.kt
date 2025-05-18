package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.BasicAXChartInfo
import br.com.asoft.nfereader.model.DocumentoFiscalHeader
import br.com.asoft.nfereader.model.NFeTotalICMS
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.TotalRecords
import java.time.LocalDate

interface DocumentoFiscalServicePort {
    fun getDocumentoFiscalHeader(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<DocumentoFiscalHeader>

    fun getFaturamentoAnualGroupedByYear(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo>

    fun getFaturamentoAnualGroupedByYearAndMonth(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo>

    fun getDistVendasEstadoValorTotal(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo>

    fun getDistVendasEstadoFrequencia(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo>

    fun getTotaisImpostos(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): NFeTotalICMS

    fun getQtdPedidosDiaADia(
        identityId: String,
        natOperacaoList: List<String>
    ): List<PedidosDiaADia>

    fun countByUserCreate(userCreate: String): TotalRecords
}