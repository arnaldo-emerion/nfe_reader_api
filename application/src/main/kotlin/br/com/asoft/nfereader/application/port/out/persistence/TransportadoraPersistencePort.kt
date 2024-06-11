package br.com.asoft.nfereader.application.port.out.persistence

import br.com.asoft.nfereader.model.Transportadora

interface TransportadoraPersistencePort {
    fun getAll(
        userCreate: String
    ): List<Transportadora>
}