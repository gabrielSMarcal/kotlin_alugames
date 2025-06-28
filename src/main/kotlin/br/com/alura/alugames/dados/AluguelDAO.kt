package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Aluguel
import javax.persistence.EntityManager

class AluguelDAO(manager: EntityManager): DAO<Aluguel, AluguelEntity> (manager, AluguelEntity::class) {

    override fun toEntity(objeto: Aluguel): AluguelEntity {
        return AluguelEntity(objeto.gamer.to, objeto.jogo)
    }

    override fun toModel(entity: AluguelEntity): Aluguel {

    }
}