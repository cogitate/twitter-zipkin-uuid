import sbt._
import Keys._

/**
 * archive resolving gets a bit tricky depending on if we're compiling in github, twitter,
 * or somewhere else
 */
object ZipkinResolver extends Plugin {

  val ostrichVersion = "9.8.0-SNAPSHOT"
  val finagleVersion = "6.25.0-SNAPSHOT"
  val utilVersion = "6.24.0-SNAPSHOT"
  val proxyRepo = Option(System.getenv("SBT_PROXY_REPO"))

  val defaultResolvers = SettingKey[Seq[Resolver]](
    "default-resolvers",
    "maven repositories to use by default, unless a proxy repo is set via SBT_PROXY_REPO"
  )

  val localRepo = SettingKey[File](
    "local-repo",
    "local folder to use as a repo (and where publish-local publishes to)"
  )

  val newSettings = Seq(
    defaultResolvers := Seq(
      // standard resolvers
      "typesafe" at "http://repo.typesafe.com/typesafe/releases",
      "ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2/",
      "twitter.com" at "http://maven.twttr.com/",
      "powermock-api" at "http://powermock.googlecode.com/svn/repo/",
      "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools/", //replaced scala-tools.org
      "oauth.net" at "http://oauth.googlecode.com/svn/code/maven",
      "download.java.net" at "http://download.java.net/maven/2/",
      "atlassian" at "https://m2proxy.atlassian.com/repository/public/",
      // for netty:
      "jboss" at "http://repository.jboss.org/nexus/content/groups/public/",
      // for storm:
      "clojars.org" at "http://clojars.org/repo",
      "conjars.org" at "http://conjars.org/repo"
    ),

    localRepo := file(System.getProperty("user.home") + "/.ivy2/local"),


    // configure resolvers for the build
    resolvers <<= (resolvers, defaultResolvers, localRepo)
    { (resolvers, defaultResolvers, localRepo) =>
      ({
        resolvers ++ defaultResolvers
      }) ++ Seq(
        // the local repo has to be in here twice, because sbt won't push to a "file:"
        // repo, but it won't read artifacts from a "Resolver.file" repo. (head -> desk)
        "local-lookup" at ("file:" + localRepo.getAbsolutePath),
        Resolver.file("Local repo", file(System.getProperty("user.home") + "/.ivy2/local"))(Resolver.ivyStylePatterns)
      )
    },

    dependencyOverrides += "com.twitter" % "finagle-core" % finagleVersion,
    dependencyOverrides += "com.twitter" % "finagle-httpx" % finagleVersion,
    dependencyOverrides += "com.twitter" % "finagle-http" % finagleVersion,
    dependencyOverrides += "com.twitter" % "ostrich" % ostrichVersion,
    dependencyOverrides += "com.twitter" % "util-core" % utilVersion,

    // don't add any special resolvers.
    externalResolvers <<= (resolvers) map identity
  )
}
