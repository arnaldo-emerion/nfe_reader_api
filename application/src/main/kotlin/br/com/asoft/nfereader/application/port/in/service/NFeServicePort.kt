package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.AnalisysTotal
import br.com.asoft.nfereader.model.ProdutoListagemNota

interface NFeServicePort {
    fun getCabecalhoNFePorProduto(
        identityId: String,
        pCodigo: String?,
        pCpfCnpj: String?,
        natOperacaoList: List<String>
    ): List<ProdutoListagemNota>
    fun getAllTotals(
        identityId: String,
        natOperacaoList: List<String>
    ): AnalisysTotal
}