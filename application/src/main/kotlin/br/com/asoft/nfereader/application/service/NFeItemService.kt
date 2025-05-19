package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.NFeItemServicePort
import br.com.asoft.nfereader.application.port.out.persistence.NFeItemPersistencePort
import br.com.asoft.nfereader.model.NFeItemModel
import org.springframework.stereotype.Service

@Service
class NFeItemService(private val nFeItemPersistencePort: NFeItemPersistencePort) : NFeItemServicePort {
    override fun findByNfeId(identityId: String, nfeId: Long): List<NFeItemModel> {
        return this.nFeItemPersistencePort.findByNfeId(identityId = identityId, nfeId = nfeId)
    }

}