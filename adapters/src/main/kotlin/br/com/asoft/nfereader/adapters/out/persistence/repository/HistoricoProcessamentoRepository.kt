package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.HistoricoProcessamentoEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoricoProcessamentoRepository : PagingAndSortingRepository<HistoricoProcessamentoEntity, Long> {
    fun findByUserCreateAndProcessadaCorretamenteFalseOrderByDataProcessamentoDesc(userCreate: String?): MutableList<HistoricoProcessamentoEntity>

}
