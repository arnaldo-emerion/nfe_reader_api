package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(
    indexes = [Index(name = "nfe_item_user_create_index", columnList = "userCreate"), Index(
        name = "nfe_item_nitem",
        columnList = "nItem ASC"
    )]
)
data class NFeItem(
    @ManyToOne
    val nfe: NFeEntity? = null,

    @OneToOne
    val produtoEntity: ProdutoEntity? = null,

    val cfop: String? = null,
    val unidade: String? = null,
    val quantidade: Double,
    val valorUnitario: Double,
    val valorTotal: Double,
    val nItem: Int? = null,

    @OneToOne(mappedBy = "nFeItem", cascade = [CascadeType.ALL])
    val nFeICMS: NFeICMS? = null,

    @OneToOne(mappedBy = "nFeItem", cascade = [CascadeType.ALL])
    val nFeIPI: NFeIPI? = null,

    @OneToOne(mappedBy = "nFeItem", cascade = [CascadeType.ALL])
    val nFePIS: NFePIS? = null,

    @OneToOne(mappedBy = "nFeItem", cascade = [CascadeType.ALL])
    val nFeCOFINS: NFeCOFINS? = null,
    override val userCreate: String
) : ByUserEntity(userCreate)
