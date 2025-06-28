package br.com.alura.alugames.dados

import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity
@Table (name = "planos")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPlano", discriminatorType = DiscriminatorType.STRING)
sealed class PlanoEntity(val tipo :String)

@Entity
@DiscriminatorValue("Avuls")
class PlanoAvulsoEntity(tipo: String): PlanoEntity(tipo)

@Entity
@DiscriminatorValue("Assinatura")
class PlanoAssunaturaEntity(
    tipo: String,
    val mendalidade: Double,
    val jogosIncluidos: Int,
    val percentualDescontoReputacao: Double): PlanoEntity(tipo)