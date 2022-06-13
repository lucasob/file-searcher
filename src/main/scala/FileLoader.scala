import java.io.File

trait FileLoader {

  /**
   * For the given directory (absolute path), return a List of
   * files within that directory.
   * @param directoryName The absolute path of a directory
   * @return A list of Files within that directory
   */
  def getFiles(directoryName: String): Option[List[File]] = {
    val directory = new File(directoryName)
    if (!directory.exists()) None
    else if (!directory.isDirectory) None
    else Some(new File(directoryName).listFiles().filter(file => file.isFile).toList)
  }
}

object Loader extends FileLoader