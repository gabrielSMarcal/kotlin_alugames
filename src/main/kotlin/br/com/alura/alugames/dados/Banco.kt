package br.com.alura.alugames.dados

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Banco {

    fun obterConexao(): Connection? {

        val dbPassword = System.getenv("MYSQL_PASS")

        return try {
            DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/alugames",
            "root",
            dbPassword)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }


}