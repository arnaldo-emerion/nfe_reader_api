package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.TotalImpostoMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.TotalImpostoApi
import br.com.asoft.nfereader.model.NFeTotalICMSDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class ImpostoController(
    private val documentoFiscalService: DocumentoFiscalServicePort,
    private val userConfigurationService: UserConfigurationServicePort,
    private val securityUtil: SecurityUtilPort,
) : TotalImpostoApi {
    override fun getGeneralStatistic(
        startDate: LocalDate?,
        endDate: LocalDate?
    ): ResponseEntity<NFeTotalICMSDTO> {
        val totaisImpostos = this.documentoFiscalService.getTotaisImpostos(
            identityId = securityUtil.getIdentityId(),
            startDate = startDate,
            endDate = endDate,
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(totaisImpostos.toDto())
    }
}