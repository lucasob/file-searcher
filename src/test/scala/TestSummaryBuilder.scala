import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class TestSummaryBuilder extends AnyFunSuite {

  test("Can extract all words in a single line") {
    val file = new File(getClass.getResource("cat.txt").getFile)
    assert(SummaryBuilder.wordsInFile(file) === List("cat"))
  }

  test("Can extract all words in multiple lines of text") {
    val file = new File(getClass.getResource("catDog.txt").getFile)
    assert(SummaryBuilder.wordsInFile(file) === List("cat", "dog"))
  }

  test("Can strip non letters from text") {
    val file = new File(getClass.getResource("catWithPunctuation.txt").getFile)
    assert(SummaryBuilder.wordsInFile(file) === List("cat"))
  }

  test("Can correctly strip non-letter chars across multiple lines and words") {
    val file = new File(getClass.getResource("multiLineWithGarbageChars.txt").getFile)
    assert(SummaryBuilder.wordsInFile(file) === List("this", "is", "full", "of", "garbage", "text", "but", "should", "still", "work"))
  }

  test("Ensures everything is in lowercase") {
    val file = new File(getClass.getResource("allUpper.txt").getFile)
    assert(SummaryBuilder.wordsInFile(file) === List("all", "in", "lower"))
  }

  test("Can construct a nested trie from a multi line file") {
    val file = new File(getClass.getResource("catCarDog.txt").getFile)

    val expectedTrie = TrieRoot[Char]()
    expectedTrie.insert("cat".toList)
    expectedTrie.insert("car".toList)
    expectedTrie.insert("dog".toList)

    assert(SummaryBuilder.filesToSummaries(List(file)) === List(FileSummary("catCarDog.txt", expectedTrie)))
  }
}
