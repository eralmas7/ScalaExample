package com.scala.example.driver

import scala.io.Source
import java.io.Writer
import java.io.PrintWriter
import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import com.scala.example.exception.IllegalInputException
import com.scala.example.regex.RegexLineParser
import com.scala.example.output.OutputWriter
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.ConsoleWrite
import com.scala.example.output.ConsoleWriter
import com.scala.example.output.FileWriter

/**
 * Main driver module which would drive the application.
 */
object MarkDownAssignment extends App {
  val outputWriter = args.length match  {
    case 1 => new ConsoleWriter
    case 2 => new FileWriter(args(1))
    case _ => showHelp 
  } 
  
  def showHelp = {
    println("Usage: java com.kreditech.assignment.driver.MarkDownAssignment <source file> [<destination file>]")
    println("Using Jar: java -jar kreditech-assignment-<version>-SNAPSHOT-jar-with-dependencies.jar <source file> [<destination file>]")
    throw new IllegalInputException("Please supply at max two arguments or atlease one argument for source file")
  }
  
  val regexLineParser = new RegexLineParser
  val htmlLines = regexLineParser.createHtml(args(0))
  outputWriter.writeData(htmlLines)
}
