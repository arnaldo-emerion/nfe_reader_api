package br.com.asoft.nfereader.adapters.out.persistence.model

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
data class GeneralConfigurationEntity(
    val qtdMaxNFe: Int
) : BaseEntity()
