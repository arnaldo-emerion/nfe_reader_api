package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.NFeItemMapper.toDomain
import br.com.asoft.nfereader.adapters.out.persistence.repository.NFeItemRepository
import br.com.asoft.nfereader.application.port.out.persistence.NFeItemPersistencePort
import br.com.asoft.nfereader.model.NFeItemModel
import org.springframework.stereotype.Service

@Service
class NFeItemPersistenceAdapter(private val nFeItemRepository: NFeItemRepository) :
    NFeItemPersistencePort {
    override fun findByNfeId(identityId: String, nfeId: Long): List<NFeItemModel> {
        return this.nFeItemRepository.findByNfeId(identityId = identityId, nfeId = nfeId).map { it.toDomain() }
    }

}