
case class File(name: String, contents: Trie[Char]) {

  /**
   * Determine the rank [0, 100] for which a file has wrt the query
   * @param query The word to be searched for
   * @return The rank of the file
   */
  def searchRank(query: List[String]): Int =
    (query.map(word => contents.contains(word.toList)).count(result => result).toFloat / query.size * 100).toInt

}