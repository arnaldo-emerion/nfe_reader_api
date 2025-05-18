package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.out.persistence.mapper.UserConfigurationMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.DocumentoFiscalServicePort
import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.service.UserConfigurationService
import br.com.asoft.nfereader.controller.ConfiguracaoUsuarioApi
import br.com.asoft.nfereader.model.UserConfigurationDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserConfigurationController2(
    private val userConfigurationService: UserConfigurationService,
    private val securityUtil: SecurityUtilPort,
    private val documentoFiscalService: DocumentoFiscalServicePort
) : ConfiguracaoUsuarioApi {

    override fun getTiposDisponiveisNFe(): ResponseEntity<List<String>> {
        val list = this.userConfigurationService.getTipoNFeDisponiveisList(securityUtil.getIdentityId())
        return ResponseEntity.ok(list)
    }

    override fun getUserConfiguration(): ResponseEntity<UserConfigurationDTO> {
        val config = this.userConfigurationService.getConfigurationForUser()
        return ResponseEntity.ok(config.toDto())
    }

    override fun getEstatiticasUtilizacao(): ResponseEntity<Long> {
        val count = this.documentoFiscalService.countByUserCreate(securityUtil.getIdentityId())
        return ResponseEntity.ok(count)
    }
}