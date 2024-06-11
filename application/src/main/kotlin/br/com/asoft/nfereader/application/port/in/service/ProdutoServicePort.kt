package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC

interface ProdutoServicePort {
    fun getEstatisticaProdutoByVolume(): List<ProdutoCurvaABC>
    fun getEstatisticaProdutoByFaturamento(): List<ProdutoCurvaABC>
    fun getEstatisticaProdutoByFrequencia(): List<ProdutoCurvaABC>
    fun getProdutoByUserCreateAndCodigo(codigo: String? = null): List<Produto>
    fun getQtdProdutosDiaADia(): List<PedidosDiaADia>
    fun getByCodigoAndUserCreate(codigo: String): Produto?
    fun getTotaisEstatisticaProduto(codigo: String): ProdutoCurvaABC
}