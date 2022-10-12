val scala212 = "2.12.16"
val scala213 = "2.13.8"

ThisBuild / scalaVersion := scala213
ThisBuild / version := "1.0.0"
ThisBuild / name := "multi-module"
ThisBuild / organization := "example"

val zioVersion = "2.0.2"

// resolvers += Resolver.url("my-company-repo", url("https://mycompany.org/repo-releases/"))

val commonDeps = Seq(

)

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-deprecation"
)

lazy val root = project
  .in(file("."))
  .aggregate(module_1, module_2)
  .settings(
    name := "multi_module",
    publish / skip := true,
    crossScalaVersions := List(scala212, scala213),
    
  )
  .settings(
    scalacOptions ++= compilerOptions ++ Seq(
      "-Xfatal-warnings"
    )
  )

lazy val module_1 = project
  .in(file("module_1"))
  .settings(
    // SBT will start a forked jvm and apply the settings
    Compile / run / fork := true,
    
    assembly / mainClass := Some("example.module_1.MainApp"),
  )
  .dependsOn(module_2)

lazy val module_2 = project
  .in(file("module_2"))
  .settings(
    name := "module_2", //can be different from the file() name
    libraryDependencies ++= Seq(
      //add more dependencies which are needed only for this module
    )
  )

//----------------------------------------------
// custom part

lazy val printerTask = taskKey[Unit]("Simple custom task")
lazy val uuidStringTask = taskKey[String]("Generate string uuid task")
lazy val uuidStringSetting = settingKey[String]("Generate string uuid setting")

printerTask := {
  val uuid = uuidStringTask.value
  println("Generated uuid from task : " + uuid)

  val uuidSetting = uuidStringSetting.value
  println("Generated uuid from setting : " + uuidSetting)

  CustomTaskPrinter.print()
}

uuidStringTask := {
  StringTask.strTask()
}

uuidStringSetting := {
  StringTask.strTask()
}

//----------------------------------------------
// alias

addCommandAlias(
  "runSpecial", 
  "; set run / javaOptions ++= Seq(\"-Dport=4567\", \"-Duser=zio-fan\"); run"
)