// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
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
}
