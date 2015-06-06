name := "play-ddd-example"

lazy val commonSettings = Seq(
  organization := "jp.dakatsuka.ddd",
  scalaVersion := "2.11.6",
  libraryDependencies ++= Seq(
    "org.specs2" %% "specs2-core"  % "3.6.1" % "provided",
    "org.specs2" %% "specs2-mock"  % "3.6.1" % "provided",
    "org.specs2" %% "specs2-junit" % "3.6.1" % "provided"
  )
)

lazy val common = project.in(file("common"))
  .settings(commonSettings: _*)

lazy val domain = project.in(file("domain"))
  .settings(commonSettings: _*)
  .dependsOn(common)

lazy val infrastructure = project.in(file("infrastructure"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalikejdbc"     %% "scalikejdbc-async" % "0.5.+",
      "com.github.mauricio" %% "mysql-async"       % "0.2.+"
    )
  )
  .dependsOn(domain)

parallelExecution in ThisBuild := false

parallelExecution in Global := false
