package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.model.UserConfiguracao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("configuracao-usuario")
class UserConfigurationController(val userConfigurationService: UserConfigurationServicePort) {

    @GetMapping
    fun getUserConfiguration(): UserConfiguracao {
        return this.userConfigurationService.getConfigurationForUser()
    }
}
