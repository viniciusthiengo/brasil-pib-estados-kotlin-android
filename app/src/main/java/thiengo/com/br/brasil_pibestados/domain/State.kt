package thiengo.com.br.brasil_pibestados.domain

import java.util.*

class State(
    val pib: Double,
    val population: String,
    val area: String,
    val perCapitaIncome: String,
    val idh: String,
    val illiteracy: String,
    val lifeExpectancy: String ){

    fun getPibFormatted() =
        String.format( Locale.GERMANY, "R\$ %,.3f bilhões (em 2015)", pib )
}