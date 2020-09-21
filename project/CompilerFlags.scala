/**
  * The following Scala compiler flags are taken from [[https://tpolecat.github.io/2017/04/25/scalac-flags.html tpolecat's blog]].
  */
object CompilerFlags {
  lazy val compilerFlags = Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:_",
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Ymacro-annotations"
  )

  private lazy val excludedReplOptions =
    Set("-Ywarn-unused:imports", "-Xfatal-warnings")

  def filterExcludedReplOptions(options: Seq[String]): Seq[String] =
    options.filterNot(excludedReplOptions)
}
