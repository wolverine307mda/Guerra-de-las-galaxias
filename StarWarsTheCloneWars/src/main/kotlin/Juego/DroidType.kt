package main.kotlin.Juego

enum class DroidType (val maxenergy : Int,
                      val minenergy : Int = 0,
){
    SW348(50),
    SW447(100),
    SW4421(150, 100)
}