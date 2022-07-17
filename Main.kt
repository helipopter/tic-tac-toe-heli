package tictactoe

fun printMap(map: MutableList<MutableList<Char>>) {
    println("""
        ---------
        | ${map[0].joinToString(" ")} |
        | ${map[1].joinToString(" ")} |
        | ${map[2].joinToString(" ")} |
        ---------
    """.trimIndent())
}
//returns true if coordinates are digits from 1 to 3
fun correctCoordinates(a:Char, b:Char): Boolean {
    var flag: Boolean = false
    if ((a.isDigit()) and b.isDigit()) {
        val aInt = a.toString().toInt()
        val bInt = b.toString().toInt()
        if ((aInt in 1..3) and (bInt in 1..3)) {
            flag = true
        }
        else println("Coordinates should be from 1 to 3!")
    }
    else println("You should enter numbers!")
    return flag
}

fun main() {
    val map = mutableListOf(mutableListOf<Char>(' ', ' ',' '),
                            mutableListOf<Char>(' ', ' ',' '),
                            mutableListOf<Char>(' ', ' ',' '))

    val charsXO = mutableListOf('X','O')
    var xWins:Boolean = false
    var oWins:Boolean = false
    var stepCounter = 0
    //empty map
    printMap(map)
    //the game
    game@ while (!xWins and !oWins and (stepCounter < 9)) {
        println("Enter the coordinates:")
        var place = readln().toMutableList()
        var a = place[0]
        var b = place.last()
        while (!correctCoordinates(a, b)) {
            continue@game
        }
        var x = a.toString().toInt() - 1
        var y = b.toString().toInt() - 1
        while (map[x][y] != ' ') {
            println("This cell is occupied! Choose another one!")
            continue@game
        }
        if (stepCounter % 2 != 0) map[x][y] = 'O'
        else map[x][y] = 'X'
        stepCounter++
        for (i in charsXO) {
            if (((map[0][0] == i) and (map[0][1] == i) and (map[0][2] == i)) or
                    ((map[1][0] == i) and (map[1][1] == i) and (map[1][2] == i)) or
                    ((map[2][0] == i) and (map[2][1] == i) and (map[2][2] == i)) or
                    ((map[0][0] == i) and (map[1][0] == i) and (map[2][0] == i)) or
                    ((map[0][1] == i) and (map[1][1] == i) and (map[2][1] == i)) or
                    ((map[0][2] == i) and (map[1][2] == i) and (map[2][2] == i)) or
                    ((map[0][0] == i) and (map[1][1] == i) and (map[2][2] == i)) or
                    ((map[0][2] == i) and (map[1][1] == i) and (map[2][0] == i))) {
                if (i == 'X') xWins = true
                else oWins = true
            }
        }
        printMap(map)
        if (xWins) println("X wins!")
        if (oWins) println("O wins!")
        if ((stepCounter == 9) and (!xWins) and (!oWins)) {
            println("Draw")
            break@game
        }
    }
}

