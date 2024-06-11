package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.EmitenteEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface EmitenteRepository : PagingAndSortingRepository<EmitenteEntity, Long> {
    fun findByUserCreate(userCreate: String): List<EmitenteEntity>
}
