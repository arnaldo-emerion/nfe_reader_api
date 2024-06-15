package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.NFeServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.controller.DashboardApi
import br.com.asoft.nfereader.model.AnalisysTotalDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DashboardController(
    private val nfeServicePort: NFeServicePort,
    private val securityUtil: SecurityUtilPort,
    private val userConfigurationService: UserConfigurationServicePort,
) : DashboardApi {

    override fun getDashBoardBasicInfo(): ResponseEntity<AnalisysTotalDTO> {
        val analisysTotal = this.nfeServicePort.getAllTotals(
            identityId = securityUtil.getIdentityId(),
            natOperacaoList = userConfigurationService.getNFeProcessaveis()
        )
        return ResponseEntity.ok(analisysTotal.toDto())
    }
}