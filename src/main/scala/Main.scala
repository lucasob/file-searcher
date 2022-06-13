

//def getFilesForDirectory: List[FileSummary] = {
//  println("Enter absolute path of directory: ")
//  val directory = readLine()
//  val trieLoader = FileTrieLoader()
//  trieLoader.tries(trieLoader.getFiles(directory))
//}
//
//def getQueryTerms: String = {
//  println("Search terms: ")
//  val terms = readLine()
//  terms
//}
//
//@tailrec def iterate(tries: List[FileSummary]): Unit = {
//  val search = getQueryTerms
//  search match {
//    case "quit" => System.exit(0)
//    case _ => println(FileTrieLoader().triesBySearchRank(tries = tries, query = search.split(" ").toList, top = 10))
//  }
//
//  iterate(tries)
//}

object Main extends App {
//  iterate(getFilesForDirectory)
  println("Hello, world")
}
