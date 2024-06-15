package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeMapper.toDto
import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeMapper.toModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.NFeRepository
import br.com.asoft.nfereader.application.port.out.persistence.NFePersistencePort
import br.com.asoft.nfereader.model.AnalisysTotal
import br.com.asoft.nfereader.model.ProdutoListagemNota
import br.com.asoft.nfereader.model.Ranking
import org.springframework.stereotype.Service

@Service
class NFePersistenceAdapter(private val nFeRepository: NFeRepository) :
    NFePersistencePort {
    override fun getCabecalhoNFePorProduto(
        identityId: String,
        pCodigo: String?,
        pCpfCnpj: String?,
        natOperacaoList: List<String>
    ): List<ProdutoListagemNota> {
        return this.nFeRepository.getCabecalhoNFePorProduto(
            identityId, pCodigo, pCpfCnpj, natOperacaoList
        ).map { it.toDomain() }
    }

    override fun getRankingFaturamento(identityId: String, natOperacaoList: List<String?>?): List<Ranking> {
        return this.nFeRepository.getRankingFaturamento(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).map { it.toDto() }
    }

    override fun getRankingQtdPedidos(identityId: String, natOperacaoList: List<String?>?): List<Ranking> {
        return this.nFeRepository.getRankingQtdPedidos(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).map { it.toDto() }
    }

    override fun getAllTotals(identityId: String, natOperacaoList: List<String>): AnalisysTotal {
        return this.nFeRepository.getAllTotals(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        ).toModel()
    }
}