package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.NFeServicePort
import br.com.asoft.nfereader.application.port.out.persistence.NFePersistencePort
import br.com.asoft.nfereader.model.AnalisysTotal
import br.com.asoft.nfereader.model.NFeCompleta
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.ProdutoListagemNota
import org.springframework.stereotype.Service

@Service
class NFeService(private val nFePersistencePort: NFePersistencePort) : NFeServicePort {

    override fun getCabecalhoNFePorProduto(
        identityId: String,
        pCodigo: String?,
        pCpfCnpj: String?,
        natOperacaoList: List<String>
    ): List<ProdutoListagemNota> {
        return this.nFePersistencePort.getCabecalhoNFePorProduto(
            identityId, pCodigo, pCpfCnpj, natOperacaoList
        )
    }

    override fun getAllTotals(identityId: String, natOperacaoList: List<String>): AnalisysTotal {
        return this.nFePersistencePort.getAllTotals(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getFaturamentoDiaADia(identityId: String, natOperacaoList: List<String>): List<PedidosDiaADia> {
        return this.nFePersistencePort.getFaturamentoDiaADia(
            identityId = identityId,
            natOperacaoList = natOperacaoList
        )
    }

    override fun getNFeById(identityId: String, id: Long): NFeCompleta {
        return this.nFePersistencePort.getNFeById(
            identityId = identityId,
            id = id
        )
    }
}