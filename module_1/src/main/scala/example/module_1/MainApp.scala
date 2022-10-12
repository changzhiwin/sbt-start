package example.module_1

import example.module_2.Message

object MainApp {
  def main(args: Array[String]): Unit = {

    println("Hi, I'm in moudle_1")

    Message.print

    val portValue = Option(System.getProperty("port"))
    val userValue = Option(System.getProperty("user"))

    println("Value from argument is: " + portValue.zip(userValue))

  }
}