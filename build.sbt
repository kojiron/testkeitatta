name := """iot-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test ,
  "com.typesafe.play" %% "play-slick" % "1.1.1" ,
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1" ,
  "com.typesafe.slick" %% "slick-codegen" %  "3.1.1" % "compile" ,
  "mysql" % "mysql-connector-java" % "5.1.38" ,
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.3" ,
  "joda-time" % "joda-time" % "2.8.2"
)

/* for schema generation app/dao/Tables.scala
slick <<= slickCodeGenTask
sourceGenerators in Compile <+= slickCodeGenTask
*/

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

/* for schema generation */ 
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged , dependencyClasspath in Compile, runner in Compile, streams) map { (dir , cp , r , s) =>
  val outputDir = (dir / "slick").getPath
  val url = "jdbc:mysql://localhost/iotserver"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.driver.MySQLDriver"
  val pkg = "dao"
  toError(r.run("slick.codegen.SourceCodeGenerator" , cp.files , Array(slickDriver , jdbcDriver , url , outputDir , pkg , "iot" , "iot"), s.log))
  val fname = outputDir + "/dao/Tables.scala"
  Seq(file(fname))
}
/* comment */
