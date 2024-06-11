package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.GeneralConfigurationMapper.toInternalModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.GeneralConfigurationRepository
import br.com.asoft.nfereader.application.port.out.persistence.GeneralConfigurationPersistencePort
import br.com.asoft.nfereader.model.GeneralConfiguration
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GeneralConfigurationPersistenceAdapter(
    private val generalConfigurationRepository: GeneralConfigurationRepository
) : GeneralConfigurationPersistencePort {
    override fun findFirst(): GeneralConfiguration? {
        val generalConfiguration = this.generalConfigurationRepository.findAll(Pageable.unpaged())
        return if (generalConfiguration.isEmpty) null else generalConfiguration.first().toInternalModel()
    }
}