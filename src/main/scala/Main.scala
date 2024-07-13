
/* PART 0: GLOBAL / TYPE DEFINIITIONS */

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

/** Groups list into a list of lists based on the boarsize
 *
 *  @matrix: list to group into smaller lists
 *  @return: list of lists of size boardsize
 */
def group[T](lst: List[T]): List[List[T]] = lst.grouped(Globals.boxsize).toList
  

/** Merges list of lists into a single List
 *
 *  @matrix: list of lists to merge 
 *  @return: list of all merged elements
 */
def ungroup[T](lst: List[List[T]]): List[T] = lst.foldRight(Nil)((acc, next) => next:::acc)


/** Checks that a list has no duplicate elements
 *
 *  @matrix: list to verify for uniqueness
 *  @return: True if all elements unique
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


def choose(e: Char): List[Char] = if e == Globals.blank then Globals.cellvals.toList else e::Nil


def choices(board: Board): Matrix[Choices] = board.map(_.map(choose))


def cp[T](lst: List[List[T]]): List[List[T]] = lst match
  case Nil => List(Nil)
  case xs :: xss => for {
    x <- xs 
    ys <- cp(xss)
  } yield x::ys


def mcp[T](matrix: Matrix[List[T]]): List[Matrix[T]] = cp(matrix.map(cp))


def sudoku_v1(board: Board): List[Board] = mcp(choices(board)).filter(correct)


/* VERSION 2 */


def single[T](lst: List[T]): Boolean = lst.length == 1


def fixed(choices: List[Choices]): Choices = ungroup(choices.filter(single))


def reduce(css: List[Choices]): List[Choices] = css.map((cs: Choices) => if single(cs) then cs else cs.diff(fixed(css)))
 

def pruneBy(fn: Matrix[Choices] => Matrix[Choices]): Matrix[Choices] => Matrix[Choices] = 
  (x: Matrix[Choices]) => fn(fn(x).map(reduce))


def prune(matrix: Matrix[Choices]): Matrix[Choices] = pruneBy(boxs)(pruneBy(cols)(pruneBy(rows)(matrix)))


def sudoku_v2(board: Board): List[Board] = mcp(prune(choices(board))).filter(correct)


/* VERSION 3 */


def safe(cm: Matrix[Choices]): Boolean = rows(cm).forall((c: List[Choices]) => nodups(fixed(c))) 
  || cols(cm).forall((c: List[Choices]) => nodups(fixed(c))) 
  || boxs(cm).forall((c: List[Choices]) => nodups(fixed(c))) 


def void(cm: Matrix[Choices]): Boolean = cm.exists(_.exists(_.length == 0))


def blocked(cm: Matrix[Choices]): Boolean = void(cm) || !safe(cm)


def minchoice(cm: Matrix[Choices]): Int = ungroup(cm.map(_.map(_.length))).filter(_ > 1).min


def expand(cm: Matrix[Choices]): List[Matrix[Choices]] = 
    val n = minchoice(cm)

    val (rows1, row::rows2) = cm.span(_.length == n): @unchecked
    val (row1, cs::row2) = row.span(_.length == n): @unchecked

    for (c <- cs) yield rows1:::List(row1:::List(c)::row2):::rows2
  

def mcp_new(matrix: Matrix[Choices]): List[Matrix[Choices]] = ungroup(expand(matrix).map(mcp_new))


def search(cm: Matrix[Choices]): List[Matrix[Choices]] = 
    if blocked(cm) then Nil 
    else if cm.forall(_.forall(single)) then List(cm)
    else ungroup(expand(cm).map((cs: Matrix[Choices]) => search(prune(cs))))


def sudoku_v3(board: Board): List[Board] = search(prune(choices(board))).map(_.map(_.head))


@main def sudoku(input: String = "", boardsize: Int = 9, boxsize: Int = 3, cellvals: String = "123456789", blank: String = "."): Unit =
  Globals.boardsize = boardsize
  Globals.boxsize = boxsize
  Globals.cellvals = cellvals
  Globals.blank = blank.head

  if input.isEmpty() 
  then 
    {

    }
  else 
    {
      
    }