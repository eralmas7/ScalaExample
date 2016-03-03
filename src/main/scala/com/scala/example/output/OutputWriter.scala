package com.scala.example.output

import java.io.PrintWriter
import java.io.File
import java.io.Writer

/**
 * Factory method which would spit out the output to appropriate stream.
 */
trait OutputWriter {
	val HEAD = "<html>\n<body>\n"
    val TAIL = "</body>\n</html>\n"

    /**
     * Method that implementer of this trait would support and would inject writer by calling default method. 
     */
    def writeData(contentList: List[String]) :Unit
    
    /**
     * Default method which would spit the output to appropriate stream.
     */
	def writeData(contentList: List[String], dataWriter: Writer) = {
	    dataWriter.write(HEAD)
	    contentList.foreach(line => dataWriter.write(line))
	    dataWriter.write(TAIL)
	}
}

class ConsoleWriter extends OutputWriter {
  
  def writeData(contentList: List[String]): Unit = {
    val dataWriter = new PrintWriter(System.out)
    try {
    	super.writeData(contentList, dataWriter)
    } finally {
		dataWriter.close
	}
  }
}

class FileWriter(fileName: String) extends OutputWriter {
  
  def writeData(contentList: List[String]): Unit = {
    val dataWriter = new PrintWriter(new File(fileName))
    try {
    	super.writeData(contentList, dataWriter)
    } finally {
		dataWriter.close
	}
  }
}