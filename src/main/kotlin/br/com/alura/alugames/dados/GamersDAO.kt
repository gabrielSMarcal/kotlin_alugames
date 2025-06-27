package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Gamer
import javax.persistence.EntityManager

class GamersDAO(private val manager: EntityManager): DAO<Gamer>() {

    override fun getLista(): List<Gamer> {

        val query = manager.createQuery("FROM GamerEntity", GamerEntity::class.java)
        return query.resultList.map { entity -> Gamer(entity.nome, entity.email,
            entity.dataNascimento, entity.usuario, entity.id) }
    }

    override fun adicionar(gamer: Gamer) {

        val entity = GamerEntity(gamer.id, gamer.nome, gamer.email,
            gamer.dataNascimento, gamer.usuario)

        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }
}