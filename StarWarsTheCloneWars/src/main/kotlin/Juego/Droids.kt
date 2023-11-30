package Juego

import main.kotlin.Juego.DroidType
import main.kotlin.Juego.Position

class Droids (
    energy : Int,
    tipo : DroidType,
    position : Position
){
    val tipo : DroidType = tipo
    var energy = energy
    var position = position
    var isalive : Boolean = true

    companion object{
        fun decidetype() : DroidType{
            val randomnum = (1..10).random()
            if (randomnum >= 5) return DroidType.SW447
            if (randomnum >= 8) return DroidType.SW348
            return DroidType.SW4421
        }
        fun decideenergy(tipo : DroidType) : Int{
            return (tipo.minenergy..tipo.maxenergy).random()
        }
        fun createrandomdroid(position : Position) : Droids{
            val tipo = decidetype()
            val energy = decideenergy(tipo)
            return Droids(energy, tipo, position)
        }
    }

    fun takedamage(dmgamount : Int){
        energy -= dmgamount
        if (energy <= 0) isalive = false
    }
}