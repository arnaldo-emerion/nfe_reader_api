package br.com.asoft.nfereader.adapters.out.persistence.model.projection

import java.time.LocalDate

interface EstatisticaDestinatarioProjection {
    val userCreate: String
    val razaoSocial: String
    val cpfCnpj: String
    val menorCompra: Double
    val maiorCompra: Double
    val totalPedidos: Double
    val valorTotalCompras: Double
    val valorMedioPedido: Double
    val dataUltimaCompra: LocalDate
    val quantidadeMediaItensPorPedido: Double
    val participacaoCliente: Double
}