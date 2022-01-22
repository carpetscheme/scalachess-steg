# ScalaChess Steg

A tool to encode/decode messages as chess games, based on [James Stanley's chess-steg](https://incoherency.co.uk/blog/stories/chess-steg.html).

Reimplemented in Scala using lichess' [scalachess library](https://github.com/ornicar/scalachess)
and compiled to JavaScript with [Scala.js](https://www.scala-js.org/) (with some help from [scalachessjs](https://github.com/veloce/scalachessjs)).

[Try it out online!](https://carpetscheme.github.io/scalachess-steg)

## Build

Requirements:
* Scala ([single command setup with coursier](https://get-coursier.io/docs/cli-setup))
* [Mill](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html)

Fetch git submodules:
```
$ git submodule update --init
```
Test ScalaChessSteg:
```
$ mill ScalaChessStegJvm.test

```
Build JavaScript (to `out/ScalaChessStegJS/fullOpt/dest/out.js`)
```
$ mill ScalaChessStegJS.fullOpt

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
