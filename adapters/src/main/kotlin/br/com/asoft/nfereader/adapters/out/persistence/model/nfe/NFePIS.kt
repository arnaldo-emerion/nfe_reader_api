package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "userCreate")])
data class NFePIS(
    @OneToOne
    @JoinColumn(name = "nfeitem_id")
    val nFeItem: NFeItem? = null,

    val cst: String? = null,
    val vBC: Double,
    val pPIS: Double,
    val vPIS: Double,
    override val userCreate: String
) : ByUserEntity(userCreate)
