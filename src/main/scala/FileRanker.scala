object FileRanker {

  /**
   * Order the tries by their matching of the provided search term
   * @param summaries - The constructed Tries for a set of Files
   * @param query - The search to be conducted
   * @param top - Indicates how many records should be returned
   * @return The tries ordered by their search rank
   */
  def summariesBySearchRank(summaries: List[FileSummary], query: List[String], top: Int): List[(String, Int)] =
    summaries.map(summary => (summary.name, summary.searchRank(query))).sortBy(_._2)(Ordering.Int.reverse).take(top)

}