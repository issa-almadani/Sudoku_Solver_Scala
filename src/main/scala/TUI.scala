
import Sudoku.{sudoku_v3, Board, Globals, showBoard}
import scala.io.StdIn.readLine
import scala.io.Source

object TUI {
  def main(args: Array[String]): Unit = {
    println("Welcome to Sudoku Solver TUI")
    println("Please input a Sudoku game to solve:")
    println("Example:")
    println(". . .    . . .    2 . .")
    println(". 8 .    . . 7    . 9 .")
    println("6 . 2    . . .    5 . .\n")
    println(". 7 .    . 6 .    . . .")
    println(". . .    9 . 1    . . .")
    println(". . .    . 2 .    . 4 .\n")
    println(". . 5    . . .    6 . 3")
    println(". 9 .    4 . .    . 7 .")
    println(". . 6    . . .    . . .\n")

    var board: Board = Nil
    var input: Boolean = true
    var done: Boolean = false

    while input do
      println("Please input Sudoku board to solve.\n")
      board = Nil
      while board.length < Globals.boardsize do
        val row = readLine().replaceAll("\\s+", "").toList
        if row.length != Globals.boardsize then 
          println(s"Reinput row, received row size ${row.length}, expected ${Globals.boardsize}.")
        else if !(row.toSet -- (Globals.blank::Globals.cellvals.toList).toSet).isEmpty then 
          println("Reinput row, received row with extraneous character(s): " +
            s"${(row.toSet -- (Globals.blank::Globals.cellvals.toList).toSet).toList.mkString(" ")}, " +
            s"please use cellvals or blank characters only: ${(Globals.blank::Globals.cellvals.toList).mkString(" ")}")
        else 
          board = board:::List(row)
      println("\n\nThe board you inputed is:")
      showBoard(board)
      print("\nIs this correct? (Y/n): ")
      done = false
      while !done do
        val response = readLine()
        
        if response == "Y" then 
          done = true
          input = false
        else if response == "n" then
          done = true
        else
          print("Is this correct? (Y/n): ")
      val x = sudoku_v3(board)

      println(s"\n Solutions Found: ${x.length}\n")

      if (x.length > 0)
        println("Sample Solution:")
        showBoard(x.head)
  }
}
