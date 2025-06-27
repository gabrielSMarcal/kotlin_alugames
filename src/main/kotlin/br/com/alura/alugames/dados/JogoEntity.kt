package br.com.alura.alugames.dados

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

// Importação para entidade
@Entity
@Table(name = "jogos")
class JogoEntity(
    val titulo:String = "Título Padrão",
    val capa:String = "Capa Padrão",
    val preco:Double = 0.0,
    val descricao:String? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0) {
}