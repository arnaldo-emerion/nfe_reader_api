package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserStatistic

interface UserConfigurationPersistencePort {
    fun findByUserCreate(userCreate: String): UserConfiguracao?
    fun getTipoNFeDisponiveisList(userCreate: String): List<String>
    fun getUserStatistic(userCreate: String): UserStatistic
}