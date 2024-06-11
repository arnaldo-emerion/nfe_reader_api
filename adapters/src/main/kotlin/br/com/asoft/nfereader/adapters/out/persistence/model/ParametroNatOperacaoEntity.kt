package br.com.asoft.nfereader.adapters.out.persistence.model

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.BaseEntity
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity

@Entity
data class ParametroNatOperacaoEntity(
    @ElementCollection
    val nfeProcessaveisList: List<String>,
    val name: String,
    val active: Boolean
) : BaseEntity()
