import Juego.Configuracion
import Juego.Galaxy

fun main(args: Array<String>){
    val tiempo : Int =0
    val configuration = validarargs(args)
    val config = Configuracion(configuration)
    val galaxia = Galaxy(config)
    galaxia.positiondroids(config)

    do{
        
    }while (tiempo<30 ||)

}
fun validarargs(args : Array<String>) : IntArray{
    val numerodroids : Int
    val numerofilas : Int
    if (args.size != 2) {
        println("Mal input")
        numerodroids = validarnumero("-1")
        numerofilas = validarnumero("-1", 5, 9)
    }else{
        numerodroids = validarnumero(args[0])
        numerofilas = validarnumero(args[1], 5, 9)
    }
    return intArrayOf(numerodroids, numerofilas)
}

fun validarnumero(input: String, min : Int = 0, max : Int = 25): Int {
    var num = input.toIntOrNull()?: -1
    do{
        if(num > max || num < min){
            println("Mal puesto...dame otro:")
            num = readln().toIntOrNull()?: -1
        }
    }while (num > max || num < min)
    return num
}
