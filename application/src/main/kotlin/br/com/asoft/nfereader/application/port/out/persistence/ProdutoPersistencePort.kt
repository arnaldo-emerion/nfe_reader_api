package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC
import br.com.asoft.nfereader.model.TotaisEstatisticaProduto
import org.springframework.data.domain.Pageable

interface ProdutoPersistencePort {

    fun getProdutoByUserCreateAndCodigo(identityId: String, codigo: String?): List<Produto>

    fun getEstatisticaProduto(
        identityId: String,
        codigo: String? = null,
        natOperacaoList: List<String>,
        pageRequest: Pageable
    ): List<ProdutoCurvaABC>

    fun getQtdProdutosDiaADia(
        identityId: String,
        natOperacaoList: List<String>
    ): List<PedidosDiaADia>

    fun getTotaisEstatisticaProduto(identityId: String): TotaisEstatisticaProduto
}