package br.com.asoft.nfereader.model

data class UserConfiguracao(
    val id: Long,
    val parametroNatOperacaoList: List<ParametroNatOperacao>
) {
    fun getNFeProcessaveis(): List<String> {
        val parametroNatOperacao = parametroNatOperacaoList.firstOrNull { it.active }
        return parametroNatOperacao?.nfeProcessaveisList ?: emptyList()

    }
}
