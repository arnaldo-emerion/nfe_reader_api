package br.com.asoft.nfereader.adapters.out.persistence.mapper

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.EmitenteEntity
import br.com.asoft.nfereader.model.Emitente
import br.com.asoft.nfereader.model.EmitenteDTO

object EmitenteMapper {

    fun Emitente.toDto() =
        EmitenteDTO(
            id = id,
            cpfCnpj = cnpj,
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            inscricaoEstadual = ie,
            crt = crt,
            uf = uf,
            municipio = municipio,
            bairro = bairro,
            telefone = telefone,
            logradouro = logradouro,
            cep = cep,
            cPais = cPais,
            xPais = xPais
        )

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