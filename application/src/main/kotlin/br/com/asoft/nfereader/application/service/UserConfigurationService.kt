package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import br.com.asoft.nfereader.application.port.`in`.service.UserConfigurationServicePort
import br.com.asoft.nfereader.application.port.out.persistence.UserConfigurationPersistencePort
import br.com.asoft.nfereader.model.ParametroNatOperacao
import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserStatistic
import org.springframework.stereotype.Service

@Service
class UserConfigurationService(
    private val userConfigurationPersistence: UserConfigurationPersistencePort,
    private val securityUtil: SecurityUtilPort
) : UserConfigurationServicePort {
    override fun getConfigurationForUser(): UserConfiguracao {
        val identity = securityUtil.getIdentityId()
        return this.userConfigurationPersistence.findByUserCreate(userCreate = identity)
            ?: UserConfiguracao(
                0, listOf(
                    getDefaultParametrizacao(identity)
                )
            )
    }

    override fun getUserStatistic(userCreate: String): UserStatistic {
        return this.userConfigurationPersistence.getUserStatistic(userCreate)
    }

    override fun getNFeProcessaveis(): List<String> {
        return getConfigurationForUser().getNFeProcessaveis()
    }

    fun getTipoNFeDisponiveisList(identity: String): List<String> {
        return this.userConfigurationPersistence.getTipoNFeDisponiveisList(identity)
    }

    private fun getDefaultParametrizacao(identity: String) = ParametroNatOperacao(
        id = 1,
        nfeProcessaveisList = this.getTipoNFeDisponiveisList(identity),
        name = "Geral",
        active = true
    )

}