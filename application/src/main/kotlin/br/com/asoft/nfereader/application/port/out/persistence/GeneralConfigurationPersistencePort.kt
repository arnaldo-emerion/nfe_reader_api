package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.GeneralConfiguration

interface GeneralConfigurationPersistencePort {
    fun findFirst(): GeneralConfiguration?
}