package Juego

import main.kotlin.Juego.Position

const val CRITICALHIT = 5
const val NORMALHIT = 1

class Galaxy (config : Configuracion){
    var galaxygrid : Array<Array<Droids?>> = Array(config.grid_size)  { Array<Droids?>(config.grid_size){ null }}
    var buffergalaxygrid = copyanothergalaxy(galaxygrid)

    private fun copyanothergalaxy(inputgalaxy : Array<Array<Droids?>>): Array<Array<Droids?>>{
        var galaxycopy : Array<Array<Droids?>> = Array(galaxygrid.size)  { Array<Droids?>(galaxygrid[1].size){ null }}
        for (i in galaxygrid.indices){
            for (j in galaxygrid[1].indices){
                galaxycopy[i][j] = inputgalaxy[i][j]
            }
        }
        return galaxycopy
    }

    fun droidspresent() : Boolean{
        for(i in galaxygrid.indices){
            for (j in galaxygrid[i].indices)
                if (galaxygrid[i][j] != null) return true
        }
        return false
    }

    fun randomscan(){
        val row = (0 ..<galaxygrid.size).random()
        val col = (0 ..<galaxygrid.size).random()
        if (galaxygrid[row][col] != null){
            shoot(Position(col, row))
        }
    }

    private fun shoot(position: Position) {
        val iscritical  = (0..100).random()
        if (iscritical <= 15) galaxygrid[position.row][position.col]!!.takedamage(CRITICALHIT) //Cant be a null since it passed a chec earlier
        else galaxygrid[position.row][position.col]!!.takedamage(NORMALHIT)
        if (galaxygrid[position.row][position.col]!!.isalive == false){
            galaxygrid[position.row][position.col] = null
            println("You killed a droid!")
        } //killing the droid
    }

    fun positiondroids(config: Configuracion){
        var droidsadded = 0
        while (droidsadded < config.num_droids){
            val fila = (0 ..<config.grid_size).random()
            val col = (0 ..<config.grid_size).random()
            if (galaxygrid[fila][col] == null){
                galaxygrid[fila][col] = Droids.createrandomdroid(Position(col,fila))
            }
        }
    }

    fun searchandmovedroids(){
        for(i in galaxygrid.indices){
            for (j in galaxygrid[i].indices)
                if (galaxygrid[i][j] != null) movedroidrecursively(Position(i,j))
        }
        galaxygrid = copyanothergalaxy(buffergalaxygrid)
    }

    private fun movedroidrecursively(position: Position){
        var row : Int = position.row
        var col : Int = position.col
        while(row == position.row && col == position.col){ //To get a new position
            row = galaxygrid.indices.random()
            col = galaxygrid[1].indices.random()
        }
        if (buffergalaxygrid[row][col] == null){
            buffergalaxygrid[row][col] = buffergalaxygrid[position.row][position.col]
            buffergalaxygrid[position.row][position.col] = null
            return
        }else movedroidrecursively(Position(row, col))
    }

    fun imprimirmatriz(){
        for (i in galaxygrid.indices){
            var fila : String = ""
            for (j in galaxygrid.indices){
                if (galaxygrid[i][j] != null) fila += "D"
                else fila += " "
            }
            println(fila)
        }
    }


}