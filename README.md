# ScalaChess Steg

A tool to encode/decode messages as chess games, based on [James Stanley's chess-steg](https://incoherency.co.uk/blog/stories/chess-steg.html).

Reimplemented in Scala using lichess' [scalachess library](https://github.com/ornicar/scalachess)
and compiled to JavaScript with [Scala.js](https://www.scala-js.org/) and [Laminar](https://laminar.dev/).

:chess_pawn: __[Try it out online!](https://carpetscheme.github.io/scalachess-steg/)__


## Build

Requires: [Mill](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html) build tool.

Fetch git submodules:
```
$ git submodule update --init
```
Run tests:
```
$ mill ScalaChessStegJvm.test
```
Build JavaScript (to `out/ChessStegJS/fullOpt.dest/out.js`):    
```
$ mill ChessStegJS.fullOpt
```
Bonus! Run as CLI:
```
$ mill ChessStegCli.run --help

Usage: scalachess-steg [--steg] [--unsteg] <Message or chess game.>

Hiding messages in chess games!

Options and flags:
    --help
        Display this help text.
    --steg
        [Default] Encode message as chess game.
    --unsteg
        Decode input from chess game.
```
