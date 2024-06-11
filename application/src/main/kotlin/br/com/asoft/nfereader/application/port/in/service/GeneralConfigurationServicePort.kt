package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.GeneralConfiguration
import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserStatistic

interface GeneralConfigurationServicePort {
    fun getFirst(): GeneralConfiguration
}