package br.com.asoft.nfereader.adapters.`in`.rest.controller

import br.com.asoft.nfereader.adapters.`in`.rest.mapper.GeneralConfigurationMapper.toDto
import br.com.asoft.nfereader.application.port.`in`.service.GeneralConfigurationServicePort
import br.com.asoft.nfereader.controller.ConfiguracaoGeralApi
import br.com.asoft.nfereader.model.ConfiguracaoGeralDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GeneralConfigurationController(private val generalConfigurationService: GeneralConfigurationServicePort) :
    ConfiguracaoGeralApi {
    override fun getConfiguracaoGeral(): ResponseEntity<ConfiguracaoGeralDTO> {
        val configuration = this.generalConfigurationService.getFirst()
        return ResponseEntity.ok(configuration.toDto())
    }
}