package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.DocumentoFiscalMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.repository.DocumentoFiscalRepository
import br.com.asoft.nfereader.application.port.out.persistence.DocumentoFiscalPersistencePort
import br.com.asoft.nfereader.model.BasicAXChartInfo
import br.com.asoft.nfereader.model.DocumentoFiscal
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.TotalImpostoNFe
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Date

@Service
class DocumentoFiscalPersistenceAdapter(
    private val documentoFiscalRepository: DocumentoFiscalRepository
) : DocumentoFiscalPersistencePort {
    override fun getDocumentoFiscalHeader(
        identityId: String,
        startDate: Date?,
        endDate: Date?,
        natOperacaoList: List<String>
    ): List<DocumentoFiscal> {
        return this.documentoFiscalRepository.getDocumentoFiscalHeader(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getFaturamentoAnualGroupedByYear(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalRepository.getFaturamentoAnualGroupedByYear(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
            .map { it.toDomain() }
    }

    override fun getFaturamentoAnualGroupedByYearAndMonth(
        identityId: String,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalRepository.getFaturamentoAnualGrouppedByYearAndMonth(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getDistVendasEstadoValorTotal(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalRepository.getDistVendasEstadoValorTotal(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getDistVendasEstadoFrequencia(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): List<BasicAXChartInfo> {
        return this.documentoFiscalRepository.getDistVendasEstadoFrequencia(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getTotaisImpostos(
        identityId: String,
        startDate: LocalDate?,
        endDate: LocalDate?,
        natOperacaoList: List<String>
    ): TotalImpostoNFe {
        return this.documentoFiscalRepository.getTotaisImpostos(
            identityId = identityId,
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = natOperacaoList
        ).toDomain()
    }

    override fun getQtdPedidosDiaADia(identityId: String, natOperacaoList: List<String>): List<PedidosDiaADia> {
        return this.documentoFiscalRepository.getQtdPedidosDiaADia(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }
}