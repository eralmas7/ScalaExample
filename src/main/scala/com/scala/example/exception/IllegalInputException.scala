package com.scala.example.exception

/**
 * Exception which would be thrown when an invalid input is being passed to the mark down application.
 */ 
class IllegalInputException(message: String) extends RuntimeException(message)