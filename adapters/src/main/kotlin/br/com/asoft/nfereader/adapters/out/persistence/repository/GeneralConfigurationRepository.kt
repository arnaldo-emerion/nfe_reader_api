package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.GeneralConfigurationEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface GeneralConfigurationRepository : PagingAndSortingRepository<GeneralConfigurationEntity, Long>