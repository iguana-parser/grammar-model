
name := "grammar-model"

organization := "iguana-parser"

version := "0.1.0"

isSnapshot := true 

scalaVersion := "2.11.7"

parallelExecution in Test := false

val localMaven = "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

resolvers += localMaven

publishTo := Some(localMaven)

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "junit" % "junit" % "4.11"
)