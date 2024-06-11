package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserStatistic

interface UserConfigurationServicePort {
    fun getConfigurationForUser(): UserConfiguracao
    fun getUserStatistic(userCreate: String): UserStatistic
    fun getNFeProcessaveis(): List<String>
}