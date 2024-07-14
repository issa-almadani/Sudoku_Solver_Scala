// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  
  val test_board = List("......2..".toList, 
                        ".8...7.9.".toList,
                        "6.2...5..".toList,
                        ".7..6....".toList,
                        "...9.1...".toList,
                        "....2..4.".toList,
                        "..5...6.3".toList,
                        ".9.4...7.".toList,
                        "..6......".toList)

  val test_board_box = List("....8.6.2".toList, 
                            ".....7...".toList,
                            "2...9.5..".toList,
                            ".7.......".toList,
                            ".6.9.1.2.".toList,
                            ".......4.".toList,
                            "..5.9...6".toList,
                            "...4.....".toList,
                            "6.3.7....".toList)

  val test_choices_board = List(List("123456789".toList, "123456789".toList, "123456789".toList, 
                                "123456789".toList, List('8'), "123456789".toList, 
                                List('6'), "123456789".toList, List('2')),
                                
                                List("123456789".toList, "123456789".toList, "123456789".toList, 
                                "123456789".toList, "123456789".toList, List('7'), 
                                "123456789".toList, "123456789".toList, "123456789".toList),
                                
                                List(List('2'), "123456789".toList, "123456789".toList, 
                                "123456789".toList, List('9'), "123456789".toList, 
                                List('5'), "123456789".toList, "123456789".toList), 
                                
                                List("123456789".toList, List('7'), "123456789".toList, 
                                "123456789".toList, "123456789".toList, "123456789".toList, 
                                "123456789".toList, "123456789".toList, "123456789".toList),

                                List("123456789".toList, List('6'), "123456789".toList, 
                                List('9'), "123456789".toList, List('1'), 
                                "123456789".toList, List('2'), "123456789".toList), 

                                List("123456789".toList, "123456789".toList, "123456789".toList, 
                                "123456789".toList, "123456789".toList, "123456789".toList, 
                                "123456789".toList, List('4'), "123456789".toList), 

                                List("123456789".toList, "123456789".toList, List('5'), 
                                "123456789".toList, List('9'), "123456789".toList, 
                                "123456789".toList, "123456789".toList, List('6')),

                                List("123456789".toList, "123456789".toList, "123456789".toList, 
                                List('4'), "123456789".toList, "123456789".toList, 
                                "123456789".toList, "123456789".toList, "123456789".toList),

                                List(List('6'), "123456789".toList, List('3'), 
                                "123456789".toList, List('7'), "123456789".toList, 
                                "123456789".toList, "123456789".toList, "123456789".toList))
                                
  test("Group Test") {
    val obtained = group(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val expected = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    assertEquals(obtained, expected)
  }

  test("Ungroup Test") {
    val obtained = ungroup(List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)))
    val expected = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    assertEquals(obtained, expected)
  }

  test("Nodups Test - Success") {
    val obtained = nodups(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    assert(obtained)
  }

  test("Nodups Test - Failure") {
    val obtained = nodups(List(1, 2, 3, 4, 5, 6, 7, 5, 9))
    assert(!obtained)
  }

  test("Rows Test") {
    val obtained = rows(test_board)
    val expected = test_board
    assertEquals(obtained, expected)
  }

  test("Cols Test") {
    val obtained = cols(test_board)
    val expected = test_board.transpose
    assertEquals(obtained, expected)
  }

  test("Boxs Test") {
    val obtained = boxs(test_board)
    val expected = test_board_box
    assertEquals(obtained, expected)
  }
  
  test("Choose Test - filled #1") {
    val obtained = choose('8')
    val expected = "8".toList
    assertEquals(obtained, expected)
  }

  test("Choose Test - filled #2") {
    val obtained = choose('1')
    val expected = "1".toList
    assertEquals(obtained, expected)
  }

  test("Choose Test - Blank") {
    val obtained = choose('.')
    val expected = "123456789".toList
    assertEquals(obtained, expected)
  }

  test("Choices Test") {
    val obtained = choices(test_board_box)
    val expected = test_choices_board
    assertEquals(obtained, expected)
  }

  test("Single Test #1") {
    val obtained = single(List(1))
    assert(obtained)
  }

  test("Single Test #2") {
    val obtained = single(Nil)
    assert(!obtained)
  }

  test("Single Test #3") {
    val obtained = single(List(1, 2))
    assert(!obtained)
  }

  test("Single Test #4") {
    val obtained = single(List(1, 2, 3, 4, 5, 6, 7, 8))
    assert(!obtained)
  }
}
