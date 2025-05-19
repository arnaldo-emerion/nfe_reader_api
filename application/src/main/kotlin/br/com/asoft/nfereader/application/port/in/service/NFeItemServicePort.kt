package br.com.asoft.nfereader.application.port.`in`.service

import br.com.asoft.nfereader.model.NFeItemModel

interface NFeItemServicePort {
    fun findByNfeId(identityId: String, nfeId: Long): List<NFeItemModel>
}