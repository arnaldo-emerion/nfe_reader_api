package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.AnaliseClienteMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DestinatarioServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.AnaliseClientesApi
import br.com.asoft.nfereader.model.AnaliseQualitativaCliente
import br.com.asoft.nfereader.model.AnaliseQualitativaClienteDTO
import br.com.asoft.nfereader.model.EstatisticaDestinatarioDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class AnaliseClienteController(
    private val destinatarioService: DestinatarioServicePort,
    private val securityUtil: SecurityUtilPort,
    private val userConfigurationService: UserConfigurationServicePort,
) : AnaliseClientesApi {

    override fun getAnaliseQualitativaValorTotal(
        startDate: LocalDate?,
        endDate: LocalDate?
    ): ResponseEntity<List<AnaliseQualitativaClienteDTO>> {
        val list = this.destinatarioService.getCurvaDeClientesPorCriterio(
            securityUtil.getIdentityId(),
            AnaliseQualitativaCliente::sum,
            userConfigurationService.getNFeProcessaveis(),
            startDate,
            endDate
        )

        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getAnaliseQualitativaQtdPedidos(
        startDate: LocalDate?,
        endDate: LocalDate?
    ): ResponseEntity<List<AnaliseQualitativaClienteDTO>> {
        val list = this.destinatarioService.getCurvaDeClientesPorCriterio(
            securityUtil.getIdentityId(),
            AnaliseQualitativaCliente::count,
            userConfigurationService.getNFeProcessaveis(),
            startDate,
            endDate
        )

        return ResponseEntity.ok(list.map { it.toDto() })
    }

    override fun getDetalhesForCurvaABC(cpfCnpj: String): ResponseEntity<EstatisticaDestinatarioDTO> {
        val estatisticaDestinatario = this.destinatarioService.getEstatisticaDestinatario(
            cpfCnpj = cpfCnpj,
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(estatisticaDestinatario.toDto())
    }
}