package br.com.asoft.nfereader.adapters.out.persistence.model.nfe

import jakarta.persistence.Entity

@Entity
data class ConfiguracaoGeral(val qtdMaxNFe: Int) : BaseEntity() {
}
