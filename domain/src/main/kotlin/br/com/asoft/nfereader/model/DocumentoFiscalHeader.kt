package br.com.asoft.nfereader.model

import java.time.LocalDate
import java.util.Date

data class DocumentoFiscalHeader(
    val tipoDocumento: String,
    val id: Long,
    val natOp: String,
    val nnf: String,
    val razaoSocial: String?,
    val chNFe: String,
    val uf: String,
    val dhEmi: LocalDate,
    val vnf: Double,
    val totalPedidos: Int,
    val cnpjDest: String
)