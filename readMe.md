Goal
=====
To convert marker types in html tags.

Instructions to build and run the application.
----------------------------------------------

##### Pre-requisite for this project:

	- Install Java8 using the instruction mentioned https://www.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html
	- Install Maven using the instruction mentioned https://maven.apache.org/install.html
	- Install Git using https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
	
##### To build the application:

  	- Clone the repository using "git clone https://github.com/eralmas7/ScalaExample.git" command.
  	- After cloning, goto the directory SpringBootWithAngular where we checked out the project.
  	- Once done, you could create executable jar using "mvn package"

##### To run the application:

	java -jar <path to ScalaExample-<version>-jar-with-dependencies.jar> <input file> [<output file>]

##### Assumptions:

	1. Its assumed that empty line in input is ignored.
	2. If input file is not present, you would get an exception.
	3. Input file is a mandatory parameter. If its not passed in command line, you would get an exception.
	4. For header tags, it's assumed that line would start with a hash (#) and it won't start with a space.
	5. Logging if any is done on console i.e. Standard output.