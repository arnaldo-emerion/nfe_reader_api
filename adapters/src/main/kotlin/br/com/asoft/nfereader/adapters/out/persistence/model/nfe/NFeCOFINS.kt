package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "userCreate")])
data class NFeCOFINS(
    @OneToOne
    @JoinColumn(name = "nfeitem_id")
    private val nFeItem: NFeItem? = null,
    private val cst: String? = null,
    private val vBC: Double,
    private val pCOFINS: Double,
    private val vCOFINS: Double,
    override val userCreate: String
) : ByUserEntity(userCreate)