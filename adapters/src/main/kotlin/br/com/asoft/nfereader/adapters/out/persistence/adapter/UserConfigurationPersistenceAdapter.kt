package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.UserConfigurationMapper.toInternalModel
import br.com.asoft.nfereader.adapters.out.persistence.mapper.UserConfigurationMapper.toModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.UserConfigurationRepository
import br.com.asoft.nfereader.application.port.out.persistence.UserConfigurationPersistencePort
import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserStatistic
import org.springframework.stereotype.Service

@Service
class UserConfigurationPersistenceAdapter(
    private val userConfigurationRepository: UserConfigurationRepository
) : UserConfigurationPersistencePort {
    override fun findByUserCreate(userCreate: String): UserConfiguracao? {
        return this.userConfigurationRepository.findByUserCreate(userCreate)?.toInternalModel()
    }

    override fun getTipoNFeDisponiveisList(userCreate: String): List<String> {
        return this.userConfigurationRepository.getTipoNFeDisponiveisList(userCreate)
    }

    override fun getUserStatistic(userCreate: String): UserStatistic {
        return this.userConfigurationRepository.getUserStatistic(userCreate).toModel()
    }
}