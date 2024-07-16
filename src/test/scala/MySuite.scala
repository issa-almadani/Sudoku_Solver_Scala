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

  val unsafe_board_rows = List(".2....2..".toList, 
                               ".8...7.9.".toList,
                               "6.2...5..".toList,
                               ".7..6....".toList,
                               "...9.1...".toList,
                               "....2..4.".toList,
                               "..5...6.3".toList,
                               ".9.4...7.".toList,
                               "..6......".toList)

  val unsafe_board_boxs = List(".2....2..".toList, 
                               ".8...7.9.".toList,
                               "6.2...5..".toList,
                               ".7..6....".toList,
                               "...9.1...".toList,
                               "....2..4.".toList,
                               "..5...6.3".toList,
                               ".9.4...7.".toList,
                               "..6.....7".toList)

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

  val test_pruned_board = List(List("134579".toList, "1345".toList, "13479".toList, 
                                    "13568".toList, "134589".toList, "345689".toList, 
                                    List('2'), "1368".toList, "14678".toList), 

                               List("1345".toList, List('8'), "134".toList, 
                                    "12356".toList, "1345".toList, List('7'), 
                                    "134".toList, List('9'), "146".toList), 

                               List(List('6'), "134".toList, List('2'), 
                                    "138".toList, "13489".toList, "3489".toList, 
                                    List('5'), "138".toList, "1478".toList), 

                               List("1234589".toList, List('7'), "13489".toList, 
                                    "358".toList, List('6'), "3458".toList, 
                                    "1389".toList, "12358".toList, "12589".toList), 

                               List("23458".toList, "23456".toList, "348".toList, 
                                    List('9'), "34578".toList, List('1'), 
                                    "378".toList, "23568".toList, "25678".toList), 
                                   
                               List("13589".toList, "1356".toList, "1389".toList, 
                                    "3578".toList, List('2'), "358".toList, 
                                    "13789".toList, List('4'), "156789".toList), 
                              
                               List("12478".toList, "124".toList, List('5'), 
                                    "1278".toList, "1789".toList, "289".toList, 
                                    List('6'), "128".toList, List('3')), 
                              
                               List("1238".toList, List('9'), "138".toList, 
                                    List('4'), "1358".toList, "23568".toList, 
                                    "18".toList, List('7'), "1258".toList), 
                                    
                               List("123478".toList, "1234".toList, List('6'), 
                                    "123578".toList, "135789".toList, "23589".toList, 
                                    "1489".toList, "1258".toList, "124589".toList))

  val test_expand = List(List(List(1), List(3, 4, 5), List(3, 4, 7, 9), 
                         List(3, 5, 6, 8), List(3, 4, 5, 8, 9), List(3, 4, 5, 6, 8, 9), 
                         List(2), List(3, 6, 8), List(4, 6, 7, 8)), 

                    List(List(3, 4, 5), List(8), List(3, 4), 
                         List(1, 2, 3, 5, 6), List(1, 3, 4, 5), List(7), 
                         List(1, 3, 4), List(9), List(1, 4, 6)), 

                    List(List(6), List(3, 4), List(2), 
                         List(1, 3, 8), List(1, 3, 4, 8, 9), List(3, 4, 8, 9), 
                         List(5), List(1, 3, 8), List(1, 4, 7, 8)), 

                    List(List(2, 3, 4, 5, 8, 9), List(7), List(1, 3, 4, 8, 9), 
                         List(3, 5, 8), List(6), List(3, 4, 5, 8), 
                         List(1, 3, 8, 9), List(1, 2, 3, 5, 8), List(1, 2, 5, 8, 9)), 

                    List(List(2, 3, 4, 5, 8), List(2, 3, 4, 5, 6), List(3, 4, 8), 
                         List(9), List(3, 4, 5, 7, 8), List(1), 
                         List(3, 7, 8), List(2, 3, 5, 6, 8), List(2, 5, 6, 7, 8)), 

                    List(List(3, 5, 8, 9), List(1, 3, 5, 6), List(1, 3, 8, 9), 
                         List(3, 5, 7, 8), List(2), List(3, 5, 8), 
                         List(1, 3, 7, 8, 9), List(4), List(1, 5, 6, 7, 8, 9)), 

                    List(List(2, 4, 7, 8), List(1, 2, 4), List(5), 
                         List(1, 2, 7, 8), List(1, 7, 8, 9), List(2, 8, 9), 
                         List(6), List(1, 2, 8), List(3)), 

                    List(List(2, 3, 8), List(9), List(1, 3, 8), 
                         List(4), List(1, 3, 5, 8), List(2, 3, 5, 6, 8), 
                         List(1, 8), List(7), List(1, 2, 5, 8)), 

                    List(List(2, 3, 4, 7, 8), List(1, 2, 3, 4), List(6), 
                         List(1, 2, 3, 5, 7, 8), List(1, 3, 5, 7, 8, 9), List(2, 3, 5, 8, 9), 
                         List(1, 4, 8, 9), List(1, 2, 5, 8), List(1, 2, 4, 5, 8, 9)))
  
  val test_expand_out = List(List(List(List(1), List(3, 4, 5), List(3, 4, 7, 9), List(3, 5, 6, 8), List(3, 4, 5, 8, 9), List(3, 4, 5, 6, 8, 9), List(2), List(3, 6, 8), List(4, 6, 7, 8)), List(List(3, 4, 5), List(8), List(3), List(1, 2, 3, 5, 6), List(1, 3, 4, 5), List(7), List(1, 3, 4), List(9), List(1, 4, 6)), List(List(6), List(3, 4), List(2), List(1, 3, 8), List(1, 3, 4, 8, 9), List(3, 4, 8, 9), List(5), List(1, 3, 8), List(1, 4, 7, 8)), List(List(2, 3, 4, 5, 8, 9), List(7), List(1, 3, 4, 8, 9), List(3, 5, 8), List(6), List(3, 4, 5, 8), List(1, 3, 8, 9), List(1, 2, 3, 5, 8), List(1, 2, 5, 8, 9)), List(List(2, 3, 4, 5, 8), List(2, 3, 4, 5, 6), List(3, 4, 8), List(9), List(3, 4, 5, 7, 8), List(1), List(3, 7, 8), List(2, 3, 5, 6, 8), List(2, 5, 6, 7, 8)), List(List(3, 5, 8, 9), List(1, 3, 5, 6), List(1, 3, 8, 9), List(3, 5, 7, 8), List(2), List(3, 5, 8), List(1, 3, 7, 8, 9), List(4), List(1, 5, 6, 7, 8, 9)), List(List(2, 4, 7, 8), List(1, 2, 4), List(5), List(1, 2, 7, 8), List(1, 7, 8, 9), List(2, 8, 9), List(6), List(1, 2, 8), List(3)), List(List(2, 3, 8), List(9), List(1, 3, 8), List(4), List(1, 3, 5, 8), List(2, 3, 5, 6, 8), List(1, 8), List(7), List(1, 2, 5, 8)), List(List(2, 3, 4, 7, 8), List(1, 2, 3, 4), List(6), List(1, 2, 3, 5, 7, 8), List(1, 3, 5, 7, 8, 9), List(2, 3, 5, 8, 9), List(1, 4, 8, 9), List(1, 2, 5, 8), List(1, 2, 4, 5, 8, 9))),
                             List(List(List(1), List(3, 4, 5), List(3, 4, 7, 9), List(3, 5, 6, 8), List(3, 4, 5, 8, 9), List(3, 4, 5, 6, 8, 9), List(2), List(3, 6, 8), List(4, 6, 7, 8)), List(List(3, 4, 5), List(8), List(4), List(1, 2, 3, 5, 6), List(1, 3, 4, 5), List(7), List(1, 3, 4), List(9), List(1, 4, 6)), List(List(6), List(3, 4), List(2), List(1, 3, 8), List(1, 3, 4, 8, 9), List(3, 4, 8, 9), List(5), List(1, 3, 8), List(1, 4, 7, 8)), List(List(2, 3, 4, 5, 8, 9), List(7), List(1, 3, 4, 8, 9), List(3, 5, 8), List(6), List(3, 4, 5, 8), List(1, 3, 8, 9), List(1, 2, 3, 5, 8), List(1, 2, 5, 8, 9)), List(List(2, 3, 4, 5, 8), List(2, 3, 4, 5, 6), List(3, 4, 8), List(9), List(3, 4, 5, 7, 8), List(1), List(3, 7, 8), List(2, 3, 5, 6, 8), List(2, 5, 6, 7, 8)), List(List(3, 5, 8, 9), List(1, 3, 5, 6), List(1, 3, 8, 9), List(3, 5, 7, 8), List(2), List(3, 5, 8), List(1, 3, 7, 8, 9), List(4), List(1, 5, 6, 7, 8, 9)), List(List(2, 4, 7, 8), List(1, 2, 4), List(5), List(1, 2, 7, 8), List(1, 7, 8, 9), List(2, 8, 9), List(6), List(1, 2, 8), List(3)), List(List(2, 3, 8), List(9), List(1, 3, 8), List(4), List(1, 3, 5, 8), List(2, 3, 5, 6, 8), List(1, 8), List(7), List(1, 2, 5, 8)), List(List(2, 3, 4, 7, 8), List(1, 2, 3, 4), List(6), List(1, 2, 3, 5, 7, 8), List(1, 3, 5, 7, 8, 9), List(2, 3, 5, 8, 9), List(1, 4, 8, 9), List(1, 2, 5, 8), List(1, 2, 4, 5, 8, 9))))
    
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

  test("Fixed Test #1") {
    val obtained = fixed(test_choices_board.head)
    val expected = List('8', '6', '2')
    assertEquals(obtained, expected)
  }

  test("Fixed Test #2") {
    val obtained = fixed(List("123456789".toList, "123456789".toList))
    val expected = Nil
    assertEquals(obtained, expected)
  }

  test("Reduce Test #1") {
    val obtained = reduce(test_choices_board.head)
    val expected = List("134579".toList, "134579".toList, "134579".toList, 
                        "134579".toList, List('8'), "134579".toList, 
                        List('6'), "134579".toList, List('2'))
    assertEquals(obtained, expected)
  }

  test("Reduce Test #2") {
    val obtained = reduce(List("123456789".toList, "123456789".toList))
    val expected = List("123456789".toList, "123456789".toList)
    assertEquals(obtained, expected)
  }

  test("Prune Test") {
    val obtained = prune(choices(test_board))
    val expected = test_pruned_board
    assertEquals(obtained, expected)
  }

  test("Safe Test #1") {
    val obtained = safe(choices(test_board))
    assert(obtained)
  }

  test("Safe Test #2") {
    val obtained = safe(test_choices_board)
    assert(!obtained)
  }

  test("Safe Test #3") {
    val obtained = safe(choices(unsafe_board_rows))
    assert(!obtained)
  }

  test("Safe Test #4") {
    val obtained = safe(choices(unsafe_board_boxs))
    assert(!obtained)
  }

  test("Void Test #1") {
    val obtained = void(choices(test_board))
    assert(!obtained)
  }

  test("Void Test #2") {
    val obtained = void(List(List(List(5, 7, 8, 9), List(), List(7, 8, 9), List(1, 3, 8), List(1, 3, 4, 8, 9), List(3, 4, 8, 9),  List(2), List(1, 3, 6), List(1, 4, 6, 7))))
    assert(obtained)
  }

  test("Blocked Test #1") {
    val obtained = blocked(choices(test_board))
    assert(!obtained)
  }

  test("Blocked Test #2") {
    val obtained = blocked(test_choices_board)
    assert(obtained)
  }

  test("Expand Test #1") {
    val obtained = expand(test_expand.map(_.map(_.map(_.toString().head))))
    val expected = test_expand_out.map(_.map(_.map(_.map(_.toString().head))))
    assertEquals(obtained, expected)
  }
}
