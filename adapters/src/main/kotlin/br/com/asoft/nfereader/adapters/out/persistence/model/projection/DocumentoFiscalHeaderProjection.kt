package br.com.asoft.nfereader.adapters.out.persistence.model.projection

import java.util.Date

interface DocumentoFiscalHeaderProjection {
    val tipoDocumento: String
    val id: Long
    val natOp: String
    val nnf: String
    val razaoSocial: String
    val chNFe: String
    val uf: String
    val dhEmi: Date
    val vnf: Double
    val totalPedidos: Int
    val cnpjDest: String
}