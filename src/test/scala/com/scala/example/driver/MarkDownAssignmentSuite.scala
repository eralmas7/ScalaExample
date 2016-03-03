package com.scala.example.driver

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.scala.example.exception.IllegalInputException
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class MarkDownAssignmentSuite extends FunSuite {
  test("Integration test where Input should be atleast input file"){
    intercept[IllegalInputException] {
    	MarkDownAssignment.main(Array());
    }
  }
  
  test("Integration test where Output should be on console and no exception should be thrown"){
	  MarkDownAssignment.main(Array("src/test/resources/input.txt"));
  }
  
  test("Integration test where Output should be in file"){
    val outputFileName = "/tmp/output.txt"
	MarkDownAssignment.main(Array("src/test/resources/input.txt", outputFileName));
    val inputDataSource = Source.fromFile(outputFileName)
    val expectedOutput = List("<html>", "<body>", "<h1> Lorem ipsum</h1>", "<p>Dolor sit amet</p>", "</body>", "</html>");
    try {
      val fileLines = inputDataSource.getLines.toList
      assert(fileLines.length == expectedOutput.length)
      assert(fileLines.equals(expectedOutput))
    } finally {
      inputDataSource.close
    }
  }
}