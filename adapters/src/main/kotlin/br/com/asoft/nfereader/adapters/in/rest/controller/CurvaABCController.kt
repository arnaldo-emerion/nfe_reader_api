package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.CurvaABCMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.CurvaAbcApi
import br.com.asoft.nfereader.model.BasicValueTotalDTO
import br.com.asoft.nfereader.model.PedidosDiaADiaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class CurvaABCController(
    private val documentoFiscalService: DocumentoFiscalServicePort,
    private val userConfigurationService: UserConfigurationServicePort,
    private val securityUtil: SecurityUtilPort,
) : CurvaAbcApi {
    override fun getCurvaFaturamentoAnual(): ResponseEntity<List<BasicValueTotalDTO>> {
        val list = this.documentoFiscalService.getFaturamentoAnualGroupedByYear(
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getCurvaFaturamentoMensal(): ResponseEntity<List<BasicValueTotalDTO>> {
        val list = this.documentoFiscalService.getFaturamentoAnualGroupedByYearAndMonth(
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getDistVendasEstadoValorTotal(
        startDate: LocalDate?,
        endDate: LocalDate?
    ): ResponseEntity<List<BasicValueTotalDTO>> {
        val list = this.documentoFiscalService.getDistVendasEstadoValorTotal(
            identityId = securityUtil.getIdentityId(),
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getDistVendasEstadoFrequencia(
        startDate: LocalDate?,
        endDate: LocalDate?
    ): ResponseEntity<List<BasicValueTotalDTO>> {
        val list = this.documentoFiscalService.getDistVendasEstadoFrequencia(
            identityId = securityUtil.getIdentityId(),
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getQtdPedidosDiaADia(): ResponseEntity<List<PedidosDiaADiaDTO>> {
        val list = this.documentoFiscalService.getQtdPedidosDiaADia(
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(list.map { it.toDto() })
    }

}