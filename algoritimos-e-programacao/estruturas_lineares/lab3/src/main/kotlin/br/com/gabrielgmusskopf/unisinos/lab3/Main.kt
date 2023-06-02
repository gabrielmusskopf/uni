package br.com.gabrielgmusskopf.unisinos.lab3

fun main() {
    println("1 Adicionar\n2 Visualizar\n")
    val candidatos = ArrayList<Candidato>()
    var i = lerInt()

    while (i == 1) {
        print("Nome: ")
        val nome = readln()
        print("Partido: ")
        val partido = readln()
        print("Idade: ")
        val idade = readln().toInt()
        print("Votos: ")
        val votos = readln().toInt()

        candidatos.add(Candidato(nome, partido, idade, votos))

        println("\n1 Adicionar\n2 Visualizar\n")
        i = lerInt()
    }

    if (candidatos.isNotEmpty()) exibirDados(candidatos)
}

private fun lerInt(): Int {
    while (true) {
        return readln().toIntOrNull() ?: continue
    }
}

private fun exibirDados(candidatos: ArrayList<Candidato>) {
    println("Mais jovem: " + candidatos.minBy { c -> c.idade })
    println("Mais velho: " + candidatos.maxBy { c -> c.idade })
    println("Mais votado: " + candidatos.maxBy { c -> c.votos })
    println("Menos votado: " + candidatos.minBy { c -> c.votos })
    println("Total de votos: " + totalVotos(candidatos))
    println("Média de votos: " + totalVotos(candidatos) / candidatos.size)
}

fun totalVotos(candidatos: List<Candidato>) : Int = candidatos.map { c -> c.votos }.reduce(Int::plus)

data class Candidato(val nome: String, val partido: String, val idade: Int, val votos: Int)
