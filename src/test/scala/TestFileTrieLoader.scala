import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.io.Source

class TestFileTrieLoader extends AnyFunSuite {

  def catTrie(): LookupFile = {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)
    LookupFile(name = "cat.txt", contents = trieRoot)
  }

  def dogTrie(): LookupFile = {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("dog".toList)
    LookupFile(name = "dog.txt", contents = trieRoot)
  }

//  test("FileTrieLoader can order two single-word tries by search rank") {
//    val ordered = FileTrieLoader().triesBySearchRank(tries = List(catTrie(), dogTrie()), query = List("cat"), top = 2)
//    val expected = List(("cat.txt", 100), ("dog.txt", 0))
//    assert(ordered === expected)
//  }
//
//  test("FileTrieLoader respected top when ordering by search rank") {
//    val ordered = FileTrieLoader().triesBySearchRank(tries = List(catTrie(), dogTrie()), query = List("cat"), top = 1)
//    val expected = List(("cat.txt", 100))
//    assert(ordered === expected)
//  }

  test("Can open a file") {

    val file = new File(getClass.getResource("file.txt").getFile)

    val t = FileTrieLoader().tries(List[File](file))
    assert(false)
  }
}
