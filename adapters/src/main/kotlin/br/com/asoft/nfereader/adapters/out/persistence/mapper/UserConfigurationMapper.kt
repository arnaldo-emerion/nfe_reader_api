package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.ParametroNatOperacaoEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.UserConfigurationEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.UserStatisticProjection
import br.com.asoft.nfereader.model.ParametroNatOperacao
import br.com.asoft.nfereader.model.ParametroNatOperacaoDTO
import br.com.asoft.nfereader.model.UserConfiguracao
import br.com.asoft.nfereader.model.UserConfigurationDTO
import br.com.asoft.nfereader.model.UserStatistic

object UserConfigurationMapper {
    fun UserConfigurationEntity.toInternalModel(): UserConfiguracao =
        UserConfiguracao(
            id = id!!,
            parametroNatOperacaoList = parametroNatOperacaoEntityList.map { it.toInternalModel() }
        )

    fun ParametroNatOperacaoEntity.toInternalModel() =
        ParametroNatOperacao(
            id = id!!,
            nfeProcessaveisList = nfeProcessaveisList,
            name = name,
            active = active
        )

    fun UserStatisticProjection.toModel() =
        UserStatistic(
            qtdMaxNFePermitidas = this.qtdMaxNfe,
            qtdNFeProcessadas = this.nfe + this.cupomFiscal
        )

    fun UserConfiguracao.toDto() =
        UserConfigurationDTO(
            id = id,
            parametroNatOperacaoList = parametroNatOperacaoList.map { it.toDto() }
        )

    fun ParametroNatOperacao.toDto() =
        ParametroNatOperacaoDTO(
            id = id,
            nfeProcessaveisList = nfeProcessaveisList,
            name = name,
            active = active
        )
}