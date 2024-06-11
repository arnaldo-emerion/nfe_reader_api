package br.com.asoft.nfereader.model

import java.time.LocalDate

data class ProdutoListagemNota(
    val id: String,
    val naturezaOperacao: String,
    val numeroNotaFiscal: String,
    val razaoSocial: String,
    val chaveAcesso: String,
    val uf: String,
    val municipio: String,
    val valorNFe: Double,
    val emissao: LocalDate,
    val cnpjDestinatario: String,
    val valorUnitario: Double,
    val quantidade: Int,
    val valorTotal: Double,
    val tipoDocumento: String
)