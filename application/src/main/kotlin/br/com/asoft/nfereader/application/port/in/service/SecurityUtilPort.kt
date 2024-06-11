package br.com.asoft.nfereader.application.port.`in`.service

interface SecurityUtilPort {
    fun getIdentityId(): String
    fun getCnpj(): String
}