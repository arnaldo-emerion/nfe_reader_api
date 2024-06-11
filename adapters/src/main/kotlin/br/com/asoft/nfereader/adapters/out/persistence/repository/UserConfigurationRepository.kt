package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.UserConfigurationEntity
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.UserStatisticProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserConfigurationRepository : PagingAndSortingRepository<UserConfigurationEntity?, Long?> {
    fun findByUserCreate(userCreate: String): UserConfigurationEntity?

    @Query(
        nativeQuery = true,
        value = "select distinct n.nat_operacao from nfe_reader.nfe n where n.user_create = :userCreate"
    )
    fun getTipoNFeDisponiveisList(@Param("userCreate") userCreate: String): List<String>

    @Query(
        nativeQuery = true,
        value = """
            select 
                count(1) nfe, 
                (select count(1) from nfe_reader.cupom_fiscal_model cfm where user_create = :userCreate) cupomFiscal,
                (select coalesce(cg.qtd_maxnfe,0) from nfe_reader.configuracao_geral cg limit 1) as qtdMaxNfe
            from nfe_reader.nfe
            where user_create = :userCreate
        """
    )
    fun getUserStatistic(@Param("userCreate") userCreate: String): UserStatisticProjection

}
