package br.com.alura.alugames.modelo

import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {

    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var idInterno: String? = null
        private set
    val jogosBuscados = mutableListOf<Jogo?>()


    constructor(nome: String, email: String, dataNascimento: String, usuario: String)
            : this(nome, email) {

        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

//    init {
//        if (nome.isNullOrBlank()){
//            throw IllegalArgumentException("Nome não pode ser vazio")
//        }
//        this.email = validarEmail()
//    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento," +
                " usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno() {

        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {

        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {

            println("Seja bem vindo! Para inicializarmos, vamos criar sua conta, informe seu nome:")
            val nome = leitura.nextLine()

            println("Informe seu e-mail:")
            val email = leitura.nextLine()

            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val resposta = leitura.nextLine()

            if (resposta.equals("s", true)) {

                println("Informe sua data de nascimento (DD/MM/AAAA):")
                val dataNascimento = leitura.nextLine()

                println("Informe seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, dataNascimento, usuario)
            } else {
                return Gamer(nome, email)
            }
        }
    }
}
