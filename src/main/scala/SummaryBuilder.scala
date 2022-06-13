import java.io.File
import scala.io.Source
import scala.util.{Failure, Success, Using}

object SummaryBuilder {

  /**
   * From a list of Java File instances, convert all of the files to a List of
   * Tries representing the contents of the file.
   *
   * It is required that every file is a valid File, and is not a directory.
   * @param files The files from disc
   * @return The list of all LookupFiles
   */
  def filesToSummaries(files: List[File]): List[FileSummary] =
    files.map(file => wordsToFileSummary(file.getName, wordsInFile(file)))


  /**
   * Given a file, extract the "words" from the file.
   * This will:
   *  - strip whitespace
   *  - remove non-letter characters
   *
   * @param file The instance of a File
   * @return the words contained in the file
   */
  def wordsInFile(file: File): List[String] = {
    val fileContents = Using(Source.fromFile(file)) { source => source.mkString}
    fileContents match {
      case Failure(_) => List.empty[String]
      case Success(value) =>
        value
          .split("\n")
          .flatMap(line => line.split(" "))
          .map(word => word.toLowerCase.toList.filter(_.isLetter).mkString)
          .filter(!_.isBlank)
          .filter(word => word.toLowerCase.toList.forall(_.isLetter))
          .toList
    }
  }


  /**
   * Given the name of a file, and the words contained within that file,
   * return a LookupFile instance, which is just the Trie representation
   * of the words within the file.
   * @param fileName The name of the file on disc
   * @param words The words within the file
   * @return
   */
  def wordsToFileSummary(fileName: String, words: List[String]): FileSummary = {
    // TODO -> Trie becomes immutable such that this can be stateless
    val trie = TrieRoot[Char]()
    words.foreach(word => trie.insert(word.toList))

    FileSummary(
      name = fileName,
      contents = trie
    )
  }
}