package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO(val manager: EntityManager): DAO<Jogo>() {

    override fun getLista(): List<Jogo> {

        val query = manager.createQuery("FROM JogoEntity", JogoEntity::class.java)
        return query.resultList.map { entity -> Jogo(entity.titulo, entity.capa,
            entity.preco, entity.descricao, entity.id) }
    }

    override fun adicionar(jogo: Jogo) {

        val entity = JogoEntity(jogo.titulo, jogo.capa,
            jogo.preco, jogo.descricao)

        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    // Abaixo, código com nível inferior, para demonstrar a facilidade do código acima

//    fun getJogos(): List<Jogo> {
//
//        val listaJogos = mutableListOf<Jogo>()
//        val conexao = obterConexao()
//
//        if (conexao != null) {
//            try {
//                val statement = conexao.createStatement()
//                val resultado = statement.executeQuery("SELECT * FROM jogos")
//
//                while (resultado.next()) {
//                    val id = resultado.getInt("id")
//                    val titulo = resultado.getString("titulo")
//                    val capa = resultado.getString("capa")
//                    val descricao = resultado.getString("descricao")
//                    val preco = resultado.getDouble("preco")
//
//                    val jogo = Jogo(titulo, capa, preco, descricao, id)
//                    listaJogos.add(jogo)
//                }
//                statement.close()
//            } finally {
//                conexao.close()
//            }
//        }
//
//        return listaJogos
//    }
//
//    fun adicionarJogo(jogo: Jogo) {
//
//        val conexao = Banco.obterConexao()
//        val insert = "INSERT INTO jogos (titulo, capa, preco, descricao) VALUES (?, ?, ?, ?)"
//
//        if (conexao != null) {
//            try {
//
//                val statement = conexao.prepareStatement(insert)
//
//                statement.setString(1, jogo.titulo)
//                statement.setString(2, jogo.capa)
//                statement.setDouble(3, jogo.preco)
//                statement.setString(4, jogo.descricao)
//
//                statement.executeUpdate()
//                statement.close()
//            } finally {
//                conexao.close()
//            }
//        }
//    }
}