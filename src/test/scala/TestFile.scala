import org.scalatest.funsuite.AnyFunSuite

class TestFile extends AnyFunSuite {

  test("A single word returns a 100 match") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)

    val file = File(name = "Test File", contents = trieRoot)

    assert(file.searchRank(List("cat")) === 100)
  }

  test("Matching one word from a two word query returns a 50 match") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)
    trieRoot.insert("car".toList)

    val file = File(name = "Test File", contents = trieRoot)

    assert(file.searchRank(List("cat", "hat")) === 50)
  }

  test("Matching 1 word from a three word query returns a 33 match") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)

    val file = File(name = "Test File", contents = trieRoot)

    assert(file.searchRank(List("cat", "hat", "mat")) === 33)
  }

}
