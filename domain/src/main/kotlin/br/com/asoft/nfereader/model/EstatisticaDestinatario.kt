package br.com.asoft.nfereader.model

import java.time.LocalDate

data class EstatisticaDestinatario(
    val userCreate: String,
    val razaoSocial: String,
    val cpfCnpj: String,
    val menorCompra: Double,
    val maiorCompra: Double,
    val totalPedidos: Double,
    val valorTotalCompras: Double,
    val valorMedioPedido: Double,
    val dataUltimaCompra: LocalDate,
    val quantidadeMediaItensPorPedido: Double,
    val participacaoCliente: Double,
    var rankingFaturamento: Int? = 0,
    var rankingQtdPedidos: Int? = 0
)