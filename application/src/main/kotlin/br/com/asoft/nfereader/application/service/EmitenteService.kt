package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.EmitenteServicePort
import br.com.asoft.nfereader.application.port.out.persistence.EmitentePersistencePort
import br.com.asoft.nfereader.model.Emitente
import org.springframework.stereotype.Service

@Service
class EmitenteService(private val emitentePersistence: EmitentePersistencePort) : EmitenteServicePort {
    override fun getAll(userCreate: String): List<Emitente> {
        return this.emitentePersistence.getAll(userCreate)
    }
}