package com.scala.example.regex

import scala.io.Source

import java.io.Writer

import com.scala.example.regex.RegexLineParser;

trait RegexParser {
  def createHtml(inputFileName: String): List[String];
}

class RegexLineParser extends RegexParser {
  
  def createHtml(inputFileName: String): List[String] = {
    val inputDataSource = Source.fromFile(inputFileName)
    try {
      inputDataSource.getLines.filterNot(_.isEmpty()).map(line => regexReplacer(line)).toList
    } finally {
      inputDataSource.close
    }
  }
  
  def regexReplacer(line: String) : String = {
    line match {
	    case RegexLineParser.HeaderSixRegex(line) => s"<h6>$line</h6>\n"
	    case RegexLineParser.HeaderFiveRegex(line) => s"<h5>$line</h5>\n"
	    case RegexLineParser.HeaderFourRegex(line) => s"<h4>$line</h4>\n"
	    case RegexLineParser.HeaderThreeRegex(line) => s"<h3>$line</h3>\n"
	    case RegexLineParser.HeaderTwoRegex(line) => s"<h2>$line</h2>\n"
	    case RegexLineParser.HeaderOneRegex(line) => s"<h1>$line</h1>\n"
	    case RegexLineParser.StrongRegex(matchedString) => RegexLineParser.StrongDataGetterRegex.replaceAllIn(line, "<strong>$1</strong>") + "\n"
	    case RegexLineParser.EmphasizeRegex(matchedString) => RegexLineParser.EmphasizeDataGetterRegex.replaceAllIn(line, "<em>$1</em>") + "\n" 
	    case RegexLineParser.AnchorRegex(beforeString, text, url, afterString) => RegexLineParser.AnchorDataGetterRegex.replaceAllIn(line, "<a href='$2'>$1</a>") + "\n"
	    case _ => s"<p>$line</p>\n"
	    }
  }
}

object RegexLineParser {
	val StrongDataGetterRegex = """\*\*(.+?)\*\*""".r;
	val EmphasizeDataGetterRegex = """\*(.+?)\*""".r;
	val AnchorDataGetterRegex = """\[(.*?)\]\((.*?)\)""".r
	val HeaderOneRegex = """^#{1}(.*?)""".r
	val HeaderTwoRegex = """^#{2}(.*?)""".r
	val HeaderThreeRegex = """^#{3}(.*?)""".r
	val HeaderFourRegex = """^#{4}(.*?)""".r
	val HeaderFiveRegex = """^#{5}(.*?)""".r
	val HeaderSixRegex = """^#{6}(.*?)""".r
	val EmphasizeRegex = """[^\*].*?\*(.+?)\*.*?""".r
	val StrongRegex = """[^\*].*?\*\*(.*?)\*\*.*?""".r
	val AnchorRegex = """([^\[].*?)\[(.*?)\]\((.*?)\)([^\[].*?)""".r
}