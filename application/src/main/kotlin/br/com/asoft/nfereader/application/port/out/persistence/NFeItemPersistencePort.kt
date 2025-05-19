package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.NFeItemModel
import br.com.asoft.nfereader.model.PedidosDiaADia
import br.com.asoft.nfereader.model.Produto
import br.com.asoft.nfereader.model.ProdutoCurvaABC
import br.com.asoft.nfereader.model.TotaisEstatisticaProduto
import org.springframework.data.domain.Pageable

interface NFeItemPersistencePort {

    fun findByNfeId(identityId: String, nfeId: Long): List<NFeItemModel>
}