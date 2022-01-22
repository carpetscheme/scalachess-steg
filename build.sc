import coursier.maven.MavenRepository
import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._

object ScalaChessSteg extends ScalaModule with ScalafmtModule {
  def scalaVersion = "2.13.6"

  override def repositoriesTask = T.task {
    super.repositoriesTask() ++ Seq(
      MavenRepository("https://raw.githubusercontent.com/ornicar/lila-maven/master")
    )
  }

  override def ivyDeps = Agg(
    ivy"org.lichess::scalachess:10.2.7",
  )

  object test extends Tests with TestModule.Utest {
    override def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.10")
  }

  override def scalacOptions = Seq(
    "-encoding",
    "utf-8",
    "-explaintypes",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Ymacro-annotations",
    "-unchecked",
    "-Xcheckinit",
    "-Xlint:adapted-args",
    "-Xlint:constant",
    "-Xlint:delayedinit-select",
    "-Xlint:deprecation",
    "-Xlint:inaccessible",
    "-Xlint:infer-any",
    "-Xlint:missing-interpolator",
    "-Xlint:nullary-unit",
    "-Xlint:option-implicit",
    "-Xlint:package-object-classes",
    "-Xlint:poly-implicit-overload",
    "-Xlint:private-shadow",
    "-Xlint:stars-align",
    "-Xlint:type-parameter-shadow",
    "-Wdead-code",
    "-Wextra-implicit",
    "-Wunused:imports",
    "-Wunused:locals",
    "-Wunused:patvars",
    "-Wunused:privates",
    "-Wunused:implicits",
    "-Wunused:params",
    "-Wvalue-discard",
    "-Xmaxerrs",
    "12"
  )

}

