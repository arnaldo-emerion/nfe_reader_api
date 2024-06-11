package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.DocumentoFiscalMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.mapper.ProdutoMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.repository.ProdutoRepository
import br.com.asoft.nfereader.application.port.out.persistence.ProdutoPersistencePort
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC
import br.com.asoft.nfereader.model.TotaisEstatisticaProduto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProdutoPersistenceAdapter(private val produtoRepository: ProdutoRepository) : ProdutoPersistencePort {
    override fun getEstatisticaProduto(
        identityId: String,
        codigo: String?,
        natOperacaoList: List<String>,
        pageRequest: Pageable
    ): List<ProdutoCurvaABC> {
        return this.produtoRepository.getEstatisticaProduto(identityId, codigo, natOperacaoList, pageRequest)
            .map { it.toDomain() }
    }

    override fun getProdutoByUserCreateAndCodigo(identityId: String, codigo: String?): List<Produto> {
        return this.produtoRepository.getProdutoByUserCreateAndCodigo(identityId = identityId, codigo = codigo)
            .map { it.toDomain() }
    }

    override fun getQtdProdutosDiaADia(identityId: String, natOperacaoList: List<String>): List<PedidosDiaADia> {
        return this.produtoRepository.getQtdProdutosDiaADia(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getTotaisEstatisticaProduto(identityId: String): TotaisEstatisticaProduto {
        return this.produtoRepository.getTotaisEstatisticaProduto(identityId = identityId).toDomain()
    }
}