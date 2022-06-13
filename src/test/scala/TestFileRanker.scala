import org.scalatest.funsuite.AnyFunSuite

class TestFileRanker extends AnyFunSuite {

  def catTrie(): Trie[Char] = {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)
    trieRoot
  }

  def dogTrie(): Trie[Char] = {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("dog".toList)
    trieRoot
  }

  test("FileTrieLoader can order two single-word tries by search rank") {
    val ordered = FileRanker.summariesBySearchRank(
      summaries = List(FileSummary("cat.txt", catTrie()), FileSummary("dog.txt", dogTrie())),
      query = List("cat"),
      top = 2
    )

    val expected = List(("cat.txt", 100), ("dog.txt", 0))

    assert(ordered === expected)
  }

  test("FileTrieLoader respected top when ordering by search rank") {
    val ordered = FileRanker.summariesBySearchRank(
      summaries = List(FileSummary("cat.txt", catTrie()), FileSummary("dog.txt", dogTrie())),
      query = List("cat"),
      top = 1
    )

    val expected = List(("cat.txt", 100))

    assert(ordered === expected)
  }
}
