package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.EmitenteMapper.toModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.EmitenteRepository
import br.com.asoft.nfereader.application.port.out.persistence.EmitentePersistencePort
import br.com.asoft.nfereader.model.Emitente
import org.springframework.stereotype.Service

@Service
class EmitentePersistenceAdapter(private val emitenteRepository: EmitenteRepository) : EmitentePersistencePort {
    override fun getAll(userCreate: String): List<Emitente> {
        return this.emitenteRepository.findByUserCreate(userCreate = userCreate).map { it.toModel() }
    }
}