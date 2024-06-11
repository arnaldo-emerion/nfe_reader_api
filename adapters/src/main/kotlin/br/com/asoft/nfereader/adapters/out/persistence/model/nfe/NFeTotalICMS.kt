package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "userCreate")])
data class NFeTotalICMS(
    @OneToOne
    val nfe: NFeEntity? = null,
    val vBC: Double,
    val vICMS: Double,
    val vBCST: Double,
    val vST: Double,
    val vProd: Double,
    val vFrete: Double,
    val vSeg: Double,
    val vDesc: Double,
    val vII: Double,
    val vIPI: Double,
    val vPIS: Double,
    val vCOFINS: Double,
    val vOutro: Double,
    val vNF: Double,
    val vICMSDeson: Double,
    val vFCP: Double,
    val vICMSUFDest: Double,
    val vICMSUFRemet: Double,
    val vFCPSTRet: Double,
    val pFCPSTRet: Double,
    val vIPIDevol: Double,
    val vTotTrib: Double,
    override val userCreate: String
) : ByUserEntity(userCreate)
