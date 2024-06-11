package br.com.asoft.nfereader.adapters.out.persistence.model

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.ByUserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "user_configuration_user_create_index", columnList = "userCreate")])
data class UserConfigurationEntity(
    @OneToMany(cascade = [CascadeType.ALL])
    val parametroNatOperacaoEntityList: List<ParametroNatOperacaoEntity>,
    override val userCreate: String
) : ByUserEntity(userCreate)
