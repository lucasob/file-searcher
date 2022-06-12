import scala.collection.mutable

trait Trie[T] {

  val children: mutable.Map[T, TrieNode[T]] = mutable.Map()
  var isTail: Boolean = false

  def insert(items: Iterable[T]): Unit = {
    items.headOption match {
      case Some(head) => children.get(head) match {
        case Some(trieNode) => trieNode.insert(items.drop(1))
        case None =>
          children += (head -> TrieNode(value = head))
          children(head).insert(items.drop(1))
      }
      case _ => isTail = true
    }
  }

  def contains(sequence: Iterable[T]): Boolean =
    sequence.headOption match {
      case Some(head) => children.get(head) match {
        case Some(trieNode) => trieNode.contains(sequence.drop(1))
        case None => false
      }
      case None => isTail
    }

}

case class TrieRoot[T]() extends Trie[T]

case class TrieNode[T](value: T) extends Trie[T]
