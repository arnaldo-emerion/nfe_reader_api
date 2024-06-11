package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "destinatario",
    indexes = [Index(name = "dest_user_create_index", columnList = "userCreate")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["cnpj", "userCreate"])]
)
data class DestinatarioEntity(
    val cnpj: String,
    val razaoSocial: String,
    val ie: String? = null,
    val uf: String? = null,
    val municipio: String? = null,
    val bairro: String? = null,
    val telefone: String? = null,
    val cep: String? = null,
    val logradouro: String? = null,
    val numero: String? = null,
    val cPais: String? = null,
    val xPais: String? = null,
    val indIEDest: String? = null,
    @ManyToOne
    val emitenteEntity: EmitenteEntity? = null,
    override val userCreate: String
) : ByUserEntity(userCreate) {

}
