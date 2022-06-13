import java.io.File
import scala.io.Source
import scala.util.{Failure, Success, Using}

trait MultiFileLoader {

  def getFiles(directory: String): List[File] = {
    new File(directory).listFiles().filter(file => file.isFile).toList
  }
}

case class FileTrieLoader() extends MultiFileLoader {

  private def toLookupFile(file: File): LookupFile = {
    val fileContents = Using(Source.fromFile(file)) { source => source.mkString}
    val words = fileContents match {
      case Failure(_) => List.empty[String]
      case Success(value) =>
        value
          .split("\n")
          .flatMap(line => line.split(" "))
          .map(word => word.toList.filter(_.isLetter).mkString)
          .filter(!_.isBlank)
          .filter(word => word.toLowerCase.toList.forall(_.isLetter))
          .toList
    }

    // TODO -> Trie becomes immutable such that this can be stateless
    val trie = TrieRoot[Char]()
    words.foreach(word => trie.insert(word.toList))

    LookupFile(
      name = file.getName,
      contents = trie
    )
  }

  /**
   * From a list of Java File instances, convert all of the files to a List of
   * Tries representing the contents of the file
   * @param files The files from disc
   * @return The list of all LookupFiles
   */
  def tries(files: List[File]): List[LookupFile] =
    files.map(file => toLookupFile(file))


  /**
   * Order the tries by their matching of the provided search term
   * @param tries - The constructed Tries for a set of Files
   * @param query - The search to be conducted
   * @param top - Indicates how many records should be returned
   * @return The tries ordered by their search rank
   */
  def triesBySearchRank(tries: List[LookupFile], query: List[String], top: Int): List[(String, Int)] =
    tries.map(trie => (trie.name, trie.searchRank(query))).sortBy(_._1).take(top)
}