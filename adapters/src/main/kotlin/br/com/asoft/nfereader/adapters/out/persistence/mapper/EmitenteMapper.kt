package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.EmitenteEntity
import br.com.asoft.nfereader.model.Emitente

object EmitenteMapper {
    fun EmitenteEntity.toModel() =
        Emitente(
            id!!,
            cnpj,
            razaoSocial,
            nomeFantasia,
            ie,
            crt,
            uf,
            municipio,
            bairro,
            telefone,
            logradouro,
            cPais,
            xPais,
            cep,
            userCreate
        )
}