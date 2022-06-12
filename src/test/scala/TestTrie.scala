import org.scalatest.funsuite.AnyFunSuite

class TestTrie extends AnyFunSuite {

  test("A single letter word can be viewed as a Single TrieNode") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert(List('a'))

    // Our only item that has no children
    val trieNodeA = trieRoot.children('a')
    assert(trieNodeA.value === 'a')
    assert(trieRoot.children('a').isTail)
    assert(trieNodeA.children.isEmpty)

    // The root is not a tail
    assert(!trieRoot.isTail)
  }

  test("A three letter word is a nested TrieNode") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert(List('a', 'b', 'c'))

    val trieNodeA = trieRoot.children('a')
    assert(!trieNodeA.isTail)
    assert(trieNodeA.value === 'a')

    val trieNodeB = trieNodeA.children('b')
    assert(!trieNodeB.isTail)
    assert(trieNodeB.value === 'b')

    val trieNodeC = trieNodeB.children('c')
    assert(trieNodeC.isTail)
    assert(trieNodeC.value === 'c')
  }

  test("Two, two letter words sharing the opening two characters form an 'inverted Y'") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)
    trieRoot.insert("car".toList)

    // Our first child, C
    val trieNodeC = trieRoot.children('c')
    assert(!trieNodeC.isTail)
    assert(trieNodeC.value === 'c')

    // The second child, A
    val trieNodeA = trieNodeC.children('a')
    assert(!trieNodeA.isTail)
    assert(trieNodeA.value === 'a')

    // Our A node has two children, T and R
    val trieNodeT = trieNodeA.children('t')
    assert(trieNodeT.isTail)
    assert(trieNodeT.value === 't')

    val trieNodeR = trieNodeA.children('r')
    assert(trieNodeR.isTail)
    assert(trieNodeR.value === 'r')
  }

  test("An 'inverted Y' Trie can be queried for a word") {
    val trieRoot = TrieRoot[Char]()
    trieRoot.insert("cat".toList)
    trieRoot.insert("car".toList)

    // The know things contained
    assert(trieRoot.contains("cat"))
    assert(trieRoot.contains("car"))

    assert(!trieRoot.contains("cap"))
    assert(!trieRoot.contains("blue"))
    assert(!trieRoot.contains(List()))
  }

}
