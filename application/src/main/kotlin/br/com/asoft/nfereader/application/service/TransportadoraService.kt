package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.TransportadoraServicePort
import br.com.asoft.nfereader.application.port.out.persistence.TransportadoraPersistencePort
import br.com.asoft.nfereader.model.Transportadora
import org.springframework.stereotype.Service

@Service
class TransportadoraService(private val transportadoraPersistence: TransportadoraPersistencePort) :
    TransportadoraServicePort {
    override fun getAll(userCreate: String): List<Transportadora> {
        return this.transportadoraPersistence.getAll(userCreate)
    }
}