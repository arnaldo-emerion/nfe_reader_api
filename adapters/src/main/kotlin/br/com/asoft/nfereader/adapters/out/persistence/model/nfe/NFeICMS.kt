package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "userCreate")])
data class NFeICMS(
    @OneToOne
    @JoinColumn(name = "nfeitem_id")
    val nFeItem: NFeItem? = null,
    val orig: Int,
    val cst: String? = null,
    val modBC: Int,
    val vBC: Double,
    val pICMS: Double,
    val vICMS: Double,
    val modBCST: Double,
    val pMVAST: Double,
    val vBCST: Double,
    val pICMSST: Double,
    val vICMSST: Double,
    override val userCreate: String
) : ByUserEntity(userCreate)
