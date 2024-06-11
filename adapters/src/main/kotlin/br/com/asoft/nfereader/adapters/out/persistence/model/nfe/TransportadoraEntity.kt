package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "transportadora",
    indexes = [Index(name = "transp_user_create_index", columnList = "userCreate")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["cnpj", "userCreate"])]
)
data class TransportadoraEntity(
    val cnpj: String,
    val razaoSocial: String,
    val ie: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val endereco: String? = null,
    override val userCreate: String
) : ByUserEntity(userCreate)
