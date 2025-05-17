package br.com.asoft.nfereader.adapters.out.persistence.model.projection

import java.time.LocalDate

interface NFeCompletaProjection {
    val id: Long
    val naturezaOperacao: String
    val tipo: String
    val chaveAcesso: String
    val numeroNotaFiscal: Long
    val dataEmissao: LocalDate

    val emitenteId: Long
    val emitenteCpfCnpj: String
    val emitenteRazaoSocial: String
    val emitenteNomeFantasia: String
    val emitenteInscricaoEstadual: String
    val crt: Int
    val emitenteUf: String
    val emitenteMunicipio: String
    val emitenteBairro: String
    val emitenteTelefone: String
    val emitenteLogradouro: String
    val emitenteCep: String
    val emitenteCodigoPais: Long
    val emitenteNomePais: String

    val destinatarioId: Long
    val destinatarioCpfCnpj: String
    val destinatarioRazaoSocial: String
    val destinatarioInscricaoEstadual: String
    val destinatarioUf: String
    val destinatarioMunicipio: String
    val destinatarioBairro: String
    val destinatarioTelefone: String
    val destinatarioLogradouro: String
    val destinatarioCep: String
    val destinatarioCodigoPais: Long
    val destinatarioNomePais: String
    val destinatarioNumero: String
    val destinatarioIndIEDest: Int

    val transportadoraId: Long?
    val transportadoraCpfCnpj: String
    val transportadoraInscricaoEstadual: String
    val transportadoraMunicipio: String
    val transportadoraUf: String
    val transportadoraRazaoSocial: String
    val transportadoraEndereco: String

    val valorNotaFiscal: Double
    val valorBaseCalculo: Double
    val valorIcms: Double
    val valorBaseCalculoST: Double
    val valorST: Double
    val valorProdutos: Double
    val valorFrete: Double
    val valorSeguro: Double
    val valorDesconto: Double
    val valorImpostoImportacao: Double
    val valorIpi: Double
    val valorCofins: Double
    val valorOutros: Double
    val valorIcmsDesonerado: Double
    val valorFcp: Double
    val valorIcmsUfDestinatario: Double
    val valorIcmsUfRemetente: Double
    val valorFcpStRetido: Double
    val valorIpiDevolucao: Double
    val valorTotalTributos: Double
    val valorPis: Double
    val percentualFcpSTRetido: Double
}