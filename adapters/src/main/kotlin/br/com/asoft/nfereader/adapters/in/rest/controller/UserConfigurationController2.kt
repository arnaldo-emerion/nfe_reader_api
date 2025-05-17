package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.service.UserConfigurationService
import br.com.asoft.nfereader.controller.ConfiguracaoUsuarioApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserConfigurationController2(
    private val userConfigurationService: UserConfigurationService,
    private val securityUtil: SecurityUtilPort
) : ConfiguracaoUsuarioApi {
    override fun getTiposDisponiveisNFe(): ResponseEntity<List<String>> {
        val list = this.userConfigurationService.getTipoNFeDisponiveisList(securityUtil.getIdentityId())
        return ResponseEntity.ok(list)
    }
}