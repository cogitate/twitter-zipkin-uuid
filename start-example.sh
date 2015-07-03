./sbt "set offline := true" "zipkin-example/run -log.level=DEBUG -genSampleTraces=true -zipkin.storage.anormdb.install=true -zipkin.storage.anormdb.db=sqlite::memory"
#./sbt "set offline := true" "zipkin-example/run -log.level=DEBUG -genSampleTraces=true -zipkin.storage.anormdb.db=sqlite::memory"
