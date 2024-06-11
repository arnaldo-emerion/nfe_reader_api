package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.TransportadoraEntity
import br.com.asoft.nfereader.model.Transportadora

object TransportadoraMapper {

    fun TransportadoraEntity.toModel() =
        Transportadora(id!!, cnpj, razaoSocial, ie, uf, municipio, endereco, userCreate)
}