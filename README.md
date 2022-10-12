# What's that
A practice about learning how sbt works.

## assembly
```
$ sbt
> assembly
```

## Cross build
Just set `crossScalaVersions := List(scala212, scala213)` in setting part.
```
// for this build
compile

// for corss build
+compile
```

## alias
```
addCommandAlias(
  "runSpecial", 
  "; set run / javaOptions ++= Seq(\"-Dport=4567\", \"-Duser=zio-fan\"); run"
)
```

# Reference
- https://blog.rockthejvm.com/sbt-tutorial/
- https://www.youtube.com/watch?v=VEEG1KnFAac&t=32s