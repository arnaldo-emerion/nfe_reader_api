package br.com.asoft.nfereader.adapters.out.persistence.adapter

import br.com.asoft.nfereader.adapters.out.persistence.mapper.TransportadoraMapper.toModel
import br.com.asoft.nfereader.adapters.out.persistence.repository.TransportadoraRepository
import br.com.asoft.nfereader.application.port.out.persistence.TransportadoraPersistencePort
import br.com.asoft.nfereader.model.Transportadora
import org.springframework.stereotype.Service

@Service
class TransportadoraPersistenceAdapter(private val transportadoraRepository: TransportadoraRepository) :
    TransportadoraPersistencePort {
    override fun getAll(userCreate: String): List<Transportadora> {
        return this.transportadoraRepository.findByUserCreate(userCreate = userCreate).map { it.toModel() }
    }
}