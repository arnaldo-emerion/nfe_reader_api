package br.com.asoft.nfereader.model

data class NFeItemModel(
    val codigo: String? = null,
    val descricao: String? = null,
    val quantidade: Double? = null,
    val unidade: String? = null,
    val valorUnitario: Double? = null,
    val valorProduto: Double? = null,
    val ncm: String? = null,
    val cfop: String? = null,
    val nItem: Long? = null
)