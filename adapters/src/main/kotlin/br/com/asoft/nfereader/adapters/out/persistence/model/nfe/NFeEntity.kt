package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.persistence.UniqueConstraint
import java.util.Date


@Entity
@Table(
    indexes = [Index(
        name = "nfe_user_create_index",
        columnList = "userCreate"
    ), Index(name = "nfe_destinatario_index", columnList = "destinatario_id"), Index(
        name = "nfe_data_emissao_index",
        columnList = "dataEmissao"
    ), Index(name = "nfe_natureza_operacao_index", columnList = "natOperacao")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["chaveNFe", "userCreate"])]
)
data class NFeEntity(
    val cUF: String? = null,
    val cNF: String? = null,
    val natOperacao: String? = null,
    val nNF: String? = null,

    @Temporal(TemporalType.DATE)
    val dataEmissao: Date? = null,
    val tpNf: String? = null,
    val cMunFG: String? = null,

    @Column(nullable = false)
    var chaveNFe: String? = null,

    @Column(length = 1024)
    var infoAdicional: String? = null,

    @OneToOne
    val emitenteEntity: EmitenteEntity? = null,

    @OneToOne
    @JoinColumn(name = "destinatario_id")
    val destinatario: DestinatarioEntity? = null,

    @OneToOne
    val transportadoraEntity: TransportadoraEntity? = null,

    @OneToMany(mappedBy = "nfe", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val nFeItemList: List<NFeItem>? = null,

    @OneToOne(mappedBy = "nfe", cascade = [CascadeType.ALL])
    val nFeTotalICMS: NFeTotalICMS? = null,

    val fileName: String? = null,
    override val userCreate: String
) : ByUserEntity(userCreate)
