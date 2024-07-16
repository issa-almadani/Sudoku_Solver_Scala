
/* PART 0: GLOBAL / TYPE DEFINIITIONS / IMPORTS */
package Sudoku

import scala.io.StdIn.readLine
import scala.io.Source
import mainargs.{main, arg, ParserForMethods, Flag}

object Globals {
  var boardsize: Int = 9
  var boxsize: Int = 3
  var cellvals: String = "123456789"
  var blank: Char = '.'
}

type Matrix[T] = List[List[T]]
type Board = Matrix[Char]
type Choices = List[Char]

/* PART 1: HELPERS */

/** Groups list into a list of lists based on the boardsize
 *
 *  @lst: list to group into smaller lists
 *  @return: list of lists of size boardsize
 */
def group[T](lst: List[T]): List[List[T]] = lst.grouped(Globals.boxsize).toList
  

/** Merges list of lists into a single List
 *
 *  @lst: list of lists to merge 
 *  @return: list of all merged elements
 */
def ungroup[T](lst: List[List[T]]): List[T] = lst.flatten


/** Checks that a list has no duplicate elements
 *
 *  @lst: list to verify for uniqueness
 *  @return: True if all elements unique else False
 */
def nodups[T](lst: List[T]): Boolean =lst match
  case Nil => true
  case x :: xs => !xs.contains(x) && nodups(xs)
  

/** Returns rows of matrix (identity of matrix)
 *
 *  @matrix: matrix to group into rows 
 *  @return: list of rows in matrix  
 */
def rows[T](matrix: Matrix[T]): Matrix[T] = matrix


/** Returns columns of matrix (Transpose of matrix)
 *
 *  @matrix: matrix to group into columns 
 *  @return: list of columns in matrix  
 */
def cols[T](matrix: Matrix[T]): Matrix[T] = matrix match
  case Nil => Nil
  case xs :: Nil => for (x <- xs) yield x::Nil
  case xs :: xss => xs.zip(cols(xss)).map((a, b) => a::b)


/** Returns a list of square boxes in matrix of the given boxsize
 *
 *  @matrix: matrix to group into boxes 
 *  @return: list of boxes 
 */
def boxs[T](matrix: Matrix[T]): Matrix[T] = ungroup(group(matrix.map(group)).map(cols)).map(ungroup)


/** Tests whether a filled board (one containing no blank characters) has different entries in each row, column and box
 *
 *  @board: Board to be tested
 *  @return: True for correct / False for incorrect
 */
def correct(board: Board): Boolean = 
  val r: Matrix[Char] = rows(board)
  val c: Matrix[Char] = cols(board)
  val b: Matrix[Char] = boxs(board)

  r.forall(nodups) && c.forall(nodups) && b.forall(nodups)


/** Chooses possible values for a given cell based on the character currently in it
 *
 *  @e: Character inside given cell
 *  @return: List of possible choices to fill in Cell (if cell already filled, then the choice is the character already in it)
 */
def choose(e: Char): List[Char] = if e == Globals.blank then Globals.cellvals.toList else e::Nil


/** This function replaces blank entries in the board with a list of possible choices
 *
 *  @board: Sudoku Board
 *  @return: Sudoku board with cells filled with a list of character choices instead of just characters 
 */
def choices(board: Board): Matrix[Choices] = board.map(_.map(choose))


def cp[T](lst: List[List[T]]): List[List[T]] = lst match
  case Nil => List(Nil)
  case xs :: xss => for {
    x <- xs 
    ys <- cp(xss)
  } yield x::ys


  // FIX
def mcp[T](matrix: Matrix[List[T]]): List[Matrix[T]] = cp(matrix.map(cp))


/** This is the first version of Sudoku that solves the board by computing every possible board via the matrix cartesian product and filtering for correct solutions
 *
 *  @matrix: board to solve 
 *  @return: list of correct solutions for the given board
 */
def sudoku_v1(board: Board): List[Board] = mcp(choices(board)).filter(correct)


/* VERSION 2 */


/** This function tests whether a list is a singleton - containing only 1 element 
 *
 *  @lst: list to test
 *  @return: boolean indicating if singleton list  
 */
def single[T](lst: List[T]): Boolean = lst.length == 1


/** This takes in a list of choices and returns the fixed entries in that list
 *
 *  @choices: list to check for fixed entries
 *  @return: fixed entries of list
 */
def fixed(choices: List[Choices]): Choices = ungroup(choices.filter(single))


/** This takes in a list of choices and reduces the possible choices in certain entries based on fixed entries
 *
 *  @css: list to refine based on fixed entries
 *  @return: refined list of choices
 */
def reduce(css: List[Choices]): List[Choices] = css.map((cs: Choices) => if single(cs) then cs else cs.diff(fixed(css)))
 

/** This helper function produces a new function that operates on a matrix of choices to apply the reduce operation
 *
 *  @fn: function to transform matrix before performing the reduce operation
 *  @return: anonymous function that can take in a matrix and perform the reduction from the given operation
 */
def pruneBy(fn: Matrix[Choices] => Matrix[Choices]): Matrix[Choices] => Matrix[Choices] = 
  (x: Matrix[Choices]) => fn(fn(x).map(reduce))


/** This function removes fixed choices from rows, columns, and boxes
 *
 *  @matrix: Matrix of choices to refine 
 *  @return: refined matrix of choices
 */
def prune(matrix: Matrix[Choices]): Matrix[Choices] = pruneBy(boxs)(pruneBy(cols)(pruneBy(rows)(matrix)))


/** This is the second version of Sudoku that prunes the board once based on illegal choices before computing the matrix cartesian product
 *
 *  @matrix: board to solve 
 *  @return: list of correct solutions for the given board
 */
def sudoku_v2(board: Board): List[Board] = mcp(prune(choices(board))).filter(correct)


/* VERSION 3 */


/** This function checks if the fixed choices of the matrix are unique, meaning it is a viable candidate for a solution
 *
 *  @cm: Matrix of choices to check 
 *  @return: boolean representing if the matrix is safe
 */
def safe(cm: Matrix[Choices]): Boolean = rows(cm).forall((c: List[Choices]) => nodups(fixed(c))) 
  && cols(cm).forall((c: List[Choices]) => nodups(fixed(c))) 
  && boxs(cm).forall((c: List[Choices]) => nodups(fixed(c))) 


/** This function checks for matrices where one entry has no possible choices left, meaning it can never lead to a solution
 *
 *  @cm: Matrix of choices to check 
 *  @return: boolean representing if the matrix has a void entry
 */
def void(cm: Matrix[Choices]): Boolean = cm.exists(_.exists(_.isEmpty))


/** This function checks for matrices that are blocked and cannot produce a solution
 *
 *  @cm: Matrix of choices to check 
 *  @return: boolean representing if the matrix is blocked
 */
def blocked(cm: Matrix[Choices]): Boolean = void(cm) || !safe(cm)


/** This helper function returns the length of the entry with the minimum number of choices in the matrix
 *
 *  @cm: Matrix of choices to find minimum entry in
 *  @return: Length of minimum list entry
 */
def minchoice(cm: Matrix[Choices]): Int = ungroup(cm.map(_.map(_.length))).filter(_ > 1).min


/** This function expands a matrix into a list of matrices by expanding the list of choices that
 *  has the minimum length in the matrix, replacing it with multiple matrices of fixed entries
 *
 *  @cm: Matrix of choices to expand
 *  @return: List of matrices expanded at minimum choice entry
 */
def expand(cm: Matrix[Choices]): List[Matrix[Choices]] = 
    val n = minchoice(cm)

    val (rows1, row::rows2) = cm.span(_.forall(_.length != n)): @unchecked
    val (row1, cs::row2) = row.span(_.length != n): @unchecked

    for (c <- cs) yield rows1:::List(row1:::List(c)::row2):::rows2
  

/** This function recursively prunes and searches for solutions for a given matrix of choices and returns a list of them, if any
 *
 *  @cm: Matrix of choices to solve
 *  @return: List of matrices that are potential solutions
 */
def search(cm: Matrix[Choices]): List[Matrix[Choices]] = 
    if blocked(cm) then Nil 
    else if cm.forall(_.forall(single)) then List(cm)
    else ungroup(expand(cm).map((cs: Matrix[Choices]) => search(prune(cs))))


/** This is the third version of Sudoku that recursively prunes and expands the board based on entries with the minimum number of choices
 *
 *  @matrix: board to solve 
 *  @return: list of correct solutions for the given board
 */  
def sudoku_v3(board: Board): List[Board] = search(prune(choices(board))).map(_.map(_.map(_.head)))



/** This helper function prints out a given board to the standard output
 *
 *  @board: board to print 
 */  
def showBoard(board: Board): Unit = board.zipWithIndex.foreach { case (row, idx) => 
  if idx % Globals.boxsize == 0 then print("\n")
  
  row.zipWithIndex.foreach { case (e, idx) => 
    if idx % Globals.boxsize == 0 then print(" ")
    print(s"$e ")
  }
  print("\n")
}


def readBoard(path: String): Board = 
  val source = Source.fromFile(path)
  val string = source.mkString.replaceAll("\\s+", "").toList
  source.close()
  string.take(Globals.boardsize * Globals.boardsize).grouped(Globals.boardsize).toList


def sudoku(boardsize: Int, boxsize: Int, cellvals: String, blank: String, input: String, output: String, max_solution_num: Int): Unit =
  Globals.boardsize = boardsize
  Globals.boxsize = boxsize
  Globals.cellvals = cellvals
  Globals.blank = blank.head

  try 
    val board = readBoard(input)
    val solutions = sudoku_v3(board)
      
    println(s"\nSolutions Found: ${solutions.length}\n")
    if (solutions.length > 0)
      println("Sample Solution:")
      showBoard(solutions.head)

  catch 
    case ex: Exception => println(s"An error occurred: ${ex.getMessage}")
    

object Sudoku{
  @main
  def run(@arg(doc = "Dimensions of board, should be a square number (Default = 9)")
          boardsize: Int = 9,
          @arg(doc = "Dimensions of box, should be square root of boardsize (Default = 3)")
          boxsize: Int = 3,
          @arg(doc = "Possible characters to use in board (Default = '123456789')")
          cellvals: String = "123456789",
          @arg(doc = "Blank character for blank entries (Default = '.')")
          blank: String = ".",
          @arg(short = 'i', doc = "Path to file containing input board (Default = './sample/test.txt') (flag: -i)")
          input: String = "./sample/test.txt",
          @arg(short = 'o', doc = "Optional path to file to store output solutions (Default = prints to standard output) (flag: -o)")
          output: String = "",
          @arg(short = 'm', doc = "Maximum number of solutions to return (Default = 1) (flag: -m)")
          max_solution_num: Int = 1) = sudoku(boardsize, boxsize, cellvals, blank, input, output, max_solution_num)
  
  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)
}
