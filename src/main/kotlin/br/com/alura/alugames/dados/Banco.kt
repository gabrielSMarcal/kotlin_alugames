package br.com.alura.alugames.dados

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object Banco {

//    fun obterConexao(): Connection? {
//
//        val dbPassword = System.getenv("MYSQL_PASS")
//
//        return try {
//            DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/alugames",
//            "root",
//            dbPassword)
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            null
//        }
//    }

    // Variável de ambiente para a senha do banco de dados
    val dbPassword = System.getenv("MYSQL_PASS")

    // Mapeamento das propriedades de conexão com o banco de dados
    val properties = mapOf(
        "javax.persistence.jdbc.password" to dbPassword
    )

    fun getEntityManager(): EntityManager {

        val factory: EntityManagerFactory =
            Persistence.createEntityManagerFactory("alugames", properties)

        return factory.createEntityManager()
    }

}