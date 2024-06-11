package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class ByUserEntity(open val userCreate: String) : BaseEntity()