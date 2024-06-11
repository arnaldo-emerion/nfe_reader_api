package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.Emitente

interface EmitenteServicePort {
    fun getAll(
        userCreate: String
    ): List<Emitente>
}