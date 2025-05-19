package br.com.asoft.nfereader.adapters.out.persistence.repository

import br.com.asoft.nfereader.adapters.out.persistence.model.nfe.NFeItem
import br.com.asoft.nfereader.adapters.out.persistence.model.projection.NFeItemProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NFeItemRepository : PagingAndSortingRepository<NFeItem, Long> {
    @Query(
        nativeQuery = true,
        value = """
            select
                p.codigo, p.descricao, item.quantidade, p.unidade, item.valor_unitario as valorUnitario, 
                item.valor_total as valorProduto, p.ncm, item.cfop, item.n_item as nItem
            from nfe_reader.nfe_item item
            left join nfe_reader.produto p on p.id  = item.produto_id
            where item.nfe_id = :nfeId
            and ( cast(:identityId as varchar(64)) is null or p.user_create = cast(:identityId as varchar(64))) 
        """
    )
    fun findByNfeId(
        @Param("identityId") identityId: String,
        @Param("nfeId") nfeId: Long
    ): List<NFeItemProjection>
}
