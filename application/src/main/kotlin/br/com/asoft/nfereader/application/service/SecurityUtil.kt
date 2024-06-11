package br.com.asoft.nfereader.application.service

import br.com.asoft.nfereader.application.port.`in`.service.SecurityUtilPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class SecurityUtil : SecurityUtilPort {
    override fun getIdentityId(): String {
        val token = SecurityContextHolder.getContext()
            .authentication.principal as Jwt
        return token.claims["cognito:username"].toString()
    }

    override fun getCnpj(): String {
        val token = SecurityContextHolder.getContext()
            .authentication.principal as Jwt
        return token.claims["custom:cnpj"].toString()
    }
}