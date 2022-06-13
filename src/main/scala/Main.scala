import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Main{

  @tailrec def iterate(summaries: List[FileSummary]): Unit = {
    val search = readLine("search: ")
    search match {
      case ":quit" => System.exit(0)
      case _ =>
        FileRanker
          .summariesBySearchRank(summaries = summaries, query = search.split(" ").toList, top = 10)
          .map(summary => s"${summary._1}: ${summary._2}%")
          .foreach(result => println(result))
    }

    iterate(summaries)
  }


  def main(args: Array[String]): Unit = {
    args.headOption match {
      case Some(directory) => Loader.getFiles(directory) match {
        case Some(files) => iterate(SummaryBuilder.filesToSummaries(files))
        case None => println("Hey that directory is no good")
      }
      case None => println("Usage: runMain Main <absolute/directory/path>")
    }
  }
}
