package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "produto",
    indexes = [Index(name = "prod_user_create_index", columnList = "userCreate")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["codigo", "userCreate"])]
)
data class ProdutoEntity(
    val codigo: String? = null,
    val ean: String? = null,
    val descricao: String? = null,
    val ncm: String? = null,
    val unidade: String? = null,
    @ManyToOne
    val emitenteEntity: EmitenteEntity? = null,
    override val userCreate: String
) : ByUserEntity(userCreate) {

}
