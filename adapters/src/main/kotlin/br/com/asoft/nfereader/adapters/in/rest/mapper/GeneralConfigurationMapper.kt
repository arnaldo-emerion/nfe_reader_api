package br.com.asoft.nfereader.adapters.`in`.rest.mapper

import br.com.asoft.nfereader.model.ConfiguracaoGeralDTO
import br.com.asoft.nfereader.model.GeneralConfiguration

object GeneralConfigurationMapper {

    fun GeneralConfiguration.toDto()=
        ConfiguracaoGeralDTO(qtdMaxNFe)
}