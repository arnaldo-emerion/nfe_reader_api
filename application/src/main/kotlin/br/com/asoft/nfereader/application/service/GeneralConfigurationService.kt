package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.GeneralConfigurationServicePort
import br.com.asoft.nfereader.application.port.out.persistence.GeneralConfigurationPersistencePort
import br.com.asoft.nfereader.model.GeneralConfiguration
import org.springframework.stereotype.Service

@Service
class GeneralConfigurationService(private val generalConfigurationPersistence: GeneralConfigurationPersistencePort) :
    GeneralConfigurationServicePort {
    override fun getFirst(): GeneralConfiguration {
        val configuration = this.generalConfigurationPersistence.findFirst()
        return configuration ?: GeneralConfiguration(0, 0)
    }
}