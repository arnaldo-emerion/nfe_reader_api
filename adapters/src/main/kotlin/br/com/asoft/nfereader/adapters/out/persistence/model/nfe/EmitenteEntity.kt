package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "emitente",
    indexes = [Index(name = "emit_user_create_index", columnList = "userCreate")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["cnpj", "userCreate"])]
)
data class EmitenteEntity(
    val cnpj: String,
    val razaoSocial: String,
    val nomeFantasia: String? = null,
    val ie: String? = null,
    val crt: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val bairro: String? = null,
    val telefone: String? = null,
    val logradouro: String? = null,
    val cPais: String? = null,
    val xPais: String? = null,
    val cep: String? = null,
    override val userCreate: String,
) : ByUserEntity(userCreate)
