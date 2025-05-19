package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface NFeItemProjection {
    val codigo: String?
    val descricao: String?
    val quantidade: Double?
    val unidade: String?
    val valorUnitario: Double?
    val valorProduto: Double?
    val ncm: String?
    val cfop: String?
    val nItem: Long
}