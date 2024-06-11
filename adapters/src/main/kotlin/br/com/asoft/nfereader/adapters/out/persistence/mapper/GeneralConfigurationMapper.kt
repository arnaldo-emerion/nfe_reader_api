package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.GeneralConfigurationEntity
import br.com.asoft.nfereader.model.GeneralConfiguration

object GeneralConfigurationMapper {

    fun GeneralConfigurationEntity.toInternalModel(): GeneralConfiguration {
        return GeneralConfiguration(
            id!!,
            qtdMaxNFe
        )
    }
}