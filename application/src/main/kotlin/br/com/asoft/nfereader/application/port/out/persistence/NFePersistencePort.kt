package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.AnalisysTotal
import br.com.asoft.nfereader.model.NFeCompleta
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.ProdutoListagemNota
import br.com.asoft.nfereader.model.Ranking

interface NFePersistencePort {
    fun getCabecalhoNFePorProduto(
        identityId: String,
        pCodigo: String?,
        pCpfCnpj: String?,
        natOperacaoList: List<String>
    ): List<ProdutoListagemNota>

    fun getRankingFaturamento(
        identityId: String,
        natOperacaoList: List<String?>?
    ): List<Ranking>

    fun getRankingQtdPedidos(
        identityId: String,
        natOperacaoList: List<String?>?
    ): List<Ranking>

    fun getAllTotals(
        identityId: String,
        natOperacaoList: List<String>
    ): AnalisysTotal

    fun getFaturamentoDiaADia(
        identityId: String,
        natOperacaoList: List<String>
    ): List<PedidosDiaADia>

    fun getNFeById(
         identityId: String,
        id: Long,
    ): NFeCompleta
}