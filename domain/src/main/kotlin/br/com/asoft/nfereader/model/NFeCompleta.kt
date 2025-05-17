package br.com.asoft.nfereader.model

import java.time.LocalDate

data class NFeCompleta(
    val id: Long,
    val naturezaOperacao: String,
    val tipo: String,
    val chaveAcesso: String,
    val numeroNotaFiscal: Long,
    val dataEmissao: LocalDate,

    val emitente: Emitente,
    val destinatario: Destinatario,
    val transportadora: Transportadora?,
    val totalICMSDTO: NFeTotalICMS
)