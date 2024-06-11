package br.com.asoft.nfereader.adapters.out.persistence.model.projection

interface ProdutoCabecalhoProjection {
    val tipoDocumento: String
    val codigo: String
    val descricao: String
    val ean: String
    val unidade: String
    val ncm: String
}