# Text Searching to determine Rank of File

## What

In short, this program:

* Consumes the contents of each file within a specified directory
* Represents that file as a Trie in Memory
* Enables searching of the generated Trie and determines the rank of each file (0 -> 100) where 100 = contains the full
  search term

## System Info
Running with sbt:

    sbt version in this project: 1.6.2
    sbt script version: 1.6.2

It runs against `scala 2.12`

Jetbrains project init seems to have set `build.sbt` as `scalaVersion 3.1.2` (I have no idea hey).  

## Invocation

To start up the program

    > sbt
    > runMain Main /Path/To/Required/Directory/

Once within the program, simply enter the words to search

    > search: <your words here>
    > {fileName}: X%
    > {fileName}: Y%

In order to quit, simply type `:quit` into the search field

    > search: :quit

## Current Gaps

* The `iterate` call is manually tested, with no code-based regression
* The logic to consume a file's contents into a List of words could be done in closer to `O(n)` time using a more
  complex `reduce` call. This was opted against in favour of readability.
* A choice was made to make the Trie representation generic. It could be set as `Char` but part of the design here is
  extensibility.
