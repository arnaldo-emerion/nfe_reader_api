package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.out.persistence.DocumentoFiscalPersistencePort
import br.com.asoft.nfereader.model.BasicAXChartInfo
import br.com.asoft.nfereader.model.DocumentoFiscalHeader
import br.com.asoft.nfereader.model.NFeTotalICMS
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.TotalRecords
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DocumentoFiscalService(private val documentoFiscalPersistence: DocumentoFiscalPersistencePort) :
    DocumentoFiscalServicePort {
    override fun getDocumentoFiscalHeader(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<DocumentoFiscalHeader> {
        return this.documentoFiscalPersistence.getDocumentoFiscalHeader(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getFaturamentoAnualGroupedByYear(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalPersistence.getFaturamentoAnualGroupedByYear(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getFaturamentoAnualGroupedByYearAndMonth(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalPersistence.getFaturamentoAnualGroupedByYearAndMonth(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getDistVendasEstadoValorTotal(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalPersistence.getDistVendasEstadoValorTotal(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        )
    }

    override

    fun getDistVendasEstadoFrequencia(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalPersistence.getDistVendasEstadoFrequencia(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getTotaisImpostos(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): NFeTotalICMS {
        return this.documentoFiscalPersistence.getTotaisImpostos(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getQtdPedidosDiaADia(identityId: String, natOperacaoList: List<String>): List<PedidosDiaADia> {
        return this.documentoFiscalPersistence.getQtdPedidosDiaADia(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
    }

    override fun countByUserCreate(userCreate: String): TotalRecords {
        return this.documentoFiscalPersistence.countByUserCreate(userCreate)
    }
}