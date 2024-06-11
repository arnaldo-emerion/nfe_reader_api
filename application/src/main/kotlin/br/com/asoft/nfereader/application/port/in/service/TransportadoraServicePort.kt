package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.Transportadora

interface TransportadoraServicePort {
    fun getAll(
        userCreate: String
    ): List<Transportadora>
}