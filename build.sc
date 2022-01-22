import mill._
import mill.scalajslib.ScalaJSModule
import mill.scalalib._
import mill.scalalib.scalafmt._

trait ScalaChessStegModule extends CommonConfig {

  def millSourcePath = build.millSourcePath / "ScalaChessSteg"
  def submoduleSourcePath = build.millSourcePath / "submodules"

  override def sources = T.sources(
    millSourcePath / "src",
    submoduleSourcePath / "scalachess" / "src" / "main",
    submoduleSourcePath / "scalalib" / "src" / "main"
  )

  override def ivyDeps = Agg(
    ivy"org.typelevel::cats-core::2.2.0",
    ivy"org.scala-lang.modules::scala-parser-combinators::1.1.2",
    ivy"io.github.cquiroz::scala-java-locales::1.2.0"
  )

}

object ScalaChessStegJvm extends ScalaChessStegModule {

  object test extends ScalaModuleTests with TestModule.Utest {
    override def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.10")
  }

}

object ScalaChessStegJs extends ScalaChessStegModule with ScalaJSModule {
  def scalaJSVersion = "1.7.0"
}

object ChessStegCli extends CommonConfig {
  override def moduleDeps = Seq(ScalaChessStegJvm)

  override def ivyDeps = Agg(
    ivy"com.monovore::decline:1.3.0"
  )
}

object ChessStegJS extends ScalaJSModule with CommonConfig {
  def scalaJSVersion = "1.7.0"

  override def moduleDeps = Seq(ScalaChessStegJs)

  override def ivyDeps = Agg(
    ivy"org.scala-js::scalajs-dom::1.1.0",
    ivy"com.raquo::laminar::0.13.1"
  )
}

trait CommonConfig extends ScalafmtModule with ScalaModule {

  def scalaVersion = "2.13.6"

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
