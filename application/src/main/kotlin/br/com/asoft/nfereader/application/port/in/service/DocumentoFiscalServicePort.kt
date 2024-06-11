package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.BasicAXChartInfo
import br.com.asoft.nfereader.model.DocumentoFiscal
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.TotalImpostoNFe
import java.time.LocalDate
import java.util.Date

interface DocumentoFiscalServicePort {
    fun getDocumentoFiscalHeader(
        identityId: String,
        startDate: Date?,
        endDate: Date?,
        natOperacaoList: List<String>
    ): List<DocumentoFiscal>

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
    ): TotalImpostoNFe

    fun getQtdPedidosDiaADia(
        identityId: String,
        natOperacaoList: List<String>
    ): List<PedidosDiaADia>
}