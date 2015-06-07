![Zipkin (doc/zipkin-logo-200x119.jpg)](https://github.com/twitter/zipkin/raw/master/doc/zipkin-logo-200x119.jpg)

[Zipkin](http://twitter.github.com/zipkin) is a distributed tracing system that helps us gather timing data for all the disparate services at Twitter.

## Full documentation

See [http://twitter.github.com/zipkin](http://twitter.github.com/zipkin)

## UUID Work 

TraceId, SpanId and ParentId are now UUID generated. 

## Compiling and Running this version
*  git clone the following:
    * https://github.com/cogitate/twitter-util-uuid
    * https://github.com/cogitate/twitter-finagle-uuid
    * https://github.com/cogitate/twitter-ostrich-uuid
    * https://github.com/cogitate/twitter-server-uuid

* Then, git checkout uuid-work branch, in all the 4 projects above.
    * compile order twitter-util-uuid, twitter-finagle-uuid, twitter-ostrich-uuid, twitter-server-uuid
    * switch to uuid-work branch here and compile

* Once everything compiles, you can run ./start-example.sh which will generate samples for sql-lite with UUID traces!
    * You may run this with Cassandra as well by copying zipkin-example/src/main/scala/com/twitter/zipkin/example/Main.scala.Cassie to
      zipkin-example/src/main/scala/com/twitter/zipkin/example/Main.scala

## Help Required and Work To-do
* Fix build scripts to run without switching to the branch (uuid-work)
    * Make release versions for twitter-util-uuid, twitter-finagle-uuid, twitter-ostrich-uuid and twitter-server-uuid so they work
      with twitter-zipkin-uuid
      
* Tests
    * Need help fixing all the tests.

* Suggested development changes
    * TraceId, SpanId and ParentId should be type aliased to make future changes simple
    * Rich implicits might be a better way to port than current port. But I am not a scala expert. 



## Get involved

Check out the #zipkin IRC channel on chat.freenode.com to see if any
developers are there for questions or live debugging tips. Otherwise,
there are two mailing lists you can use to get in touch with other
users and developers.

Users: [https://groups.google.com/group/zipkin-user](https://groups.google.com/group/zipkin-user)

Developers: [https://groups.google.com/group/zipkin-dev](https://groups.google.com/group/zipkin-dev)

## Issues

Noticed a bug? [https://github.com/twitter/zipkin/issues](https://github.com/twitter/zipkin/issues)

## Contributing

See [CONTRIBUTING.md](https://github.com/twitter/zipkin/blob/master/CONTRIBUTING.md) for guidelines.

Areas where we'd love to see contributions:

* adding tracing to more libraries and protocols
* interesting reports generated with Hadoop from the trace data
* extending collector to support more transports and storage systems
* trace data visualizations in the web UI
