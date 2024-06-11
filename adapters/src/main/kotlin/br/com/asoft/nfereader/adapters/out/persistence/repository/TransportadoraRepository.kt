package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.TransportadoraEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransportadoraRepository : PagingAndSortingRepository<TransportadoraEntity, Long> {
    fun findByUserCreate(userCreate: String): List<TransportadoraEntity>
}
