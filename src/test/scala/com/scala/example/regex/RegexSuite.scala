package com.scala.example.regex

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RegexSuite extends FunSuite with BeforeAndAfter {

  var regex: RegexLineParser = _
 
  before {
    regex = new RegexLineParser
  }
  
  test("Regex should be recognized for emphasize tag"){
    val value = regex.regexReplacer("Let see if i get * emphasize * emphasize tag")
    assert (value.equals("Let see if i get <em> emphasize </em> emphasize tag\n"))
  }
  
    test("Regex should be recognized for multiple emphasize tag"){
    val value = regex.regexReplacer("Let see if i get * emphasize * emphasize tag and * this one too *")
    assert (value.equals("Let see if i get <em> emphasize </em> emphasize tag and <em> this one too </em>\n"))
  }
  
  test("Regex should not replace for an empty emphasize tag"){
    val value = regex.regexReplacer("Let see if i get * emphasize emphasize tag")
    assert (value.equals("<p>Let see if i get * emphasize emphasize tag</p>\n"))
  }
    
  test("Regex should be recognized for strong tag"){
    val value = regex.regexReplacer("Let see if i get ** strong ** strong tag")
    assert (value.equals("Let see if i get <strong> strong </strong> strong tag\n"))
  }
  
    test("Regex should be recognized for multiple strong tag"){
    val value = regex.regexReplacer("Let see if i get ** strong ** strong tag and ** this one too **")
    assert (value.equals("Let see if i get <strong> strong </strong> strong tag and <strong> this one too </strong>\n"))
  }
  
  test("Regex should not replace for an empty strong tag"){
    val value = regex.regexReplacer("Let see if i get ** strong strong tag")
    assert (value.equals("<p>Let see if i get ** strong strong tag</p>\n"))
  }

  test("Regex should recognize h6 tag"){
    val value = regex.regexReplacer("######This is h6 tag")
    assert (value.equals("<h6>This is h6 tag</h6>\n"))
  }
  
  test("Regex should recognize h5 tag"){
    val value = regex.regexReplacer("#####This is h5 tag")
    assert (value.equals("<h5>This is h5 tag</h5>\n"))
  }

  test("Regex should recognize h4 tag"){
    val value = regex.regexReplacer("####This is h4 tag")
    assert (value.equals("<h4>This is h4 tag</h4>\n"))
  }

  test("Regex should recognize h3 tag"){
    val value = regex.regexReplacer("###This is h3 tag")
    assert (value.equals("<h3>This is h3 tag</h3>\n"))
  }

  test("Regex should recognize h2 tag"){
    val value = regex.regexReplacer("##This is h2 tag")
    assert (value.equals("<h2>This is h2 tag</h2>\n"))
  }

  test("Regex should recognize h1 tag"){
    val value = regex.regexReplacer("#This is h1 tag")
    assert (value.equals("<h1>This is h1 tag</h1>\n"))
  }

  test("Regex should not recognize h1 tag if it doesnt starts with a hash"){
    val value = regex.regexReplacer(" #This is not a h1 tag")
    assert (value.equals("<p> #This is not a h1 tag</p>\n"))
  }
  
  test("Regex should recognize anchor tag"){
    val value = regex.regexReplacer("This is [example link](http://example.com/) a link")
    assert (value.equals("This is <a href='http://example.com/'>example link</a> a link\n"))
  }

  test("Regex should recognize anchor tag occuring multiple times in same statement"){
    val value = regex.regexReplacer("This is [example link](http://example.com/) a link and this one is another [sample link](http://sample.com/) one.")
    assert (value.equals("This is <a href='http://example.com/'>example link</a> a link and this one is another <a href='http://sample.com/'>sample link</a> one.\n"))
  }

  test("Integration test which should be able to recognize various samples from input file"){
    val value = regex.createHtml("src/test/resources/myfile.txt")
    assert (value.size == 10)
  }
}