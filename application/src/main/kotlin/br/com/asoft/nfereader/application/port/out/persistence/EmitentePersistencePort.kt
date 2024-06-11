package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.Emitente

interface EmitentePersistencePort {
    fun getAll(
        userCreate: String
    ): List<Emitente>
}