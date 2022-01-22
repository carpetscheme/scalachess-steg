package steg

import cats.data.Validated
import cats.data.Validated.{invalid, valid}
import chess.format.pgn._
import chess.{Move, MoveOrDrop, Pos}

import scala.annotation.tailrec

object Steg {

  def encode(message: String): String =
    encodeNum(BigInt(message.getBytes))

  def decode(chess: String): Validated[String, String] =
    decodeNum(chess)
      .map(_.toByteArray.map(_.toChar).mkString)

  def encodeNum(bigInt: BigInt): String =
    steg(bigInt, chess.Setup(chess.variant.Standard)).pgnMoves.mkString(" ")

  def decodeNum(in: String): Validated[String, BigInt] =
    Reader
      .full(in)
      .andThen(_.valid)
      .andThen(g => onlyMoves(g.moves))
      .map(unSteg)

  @tailrec
  private def steg(num: BigInt, game: chess.Game): chess.Game =
    if (num < 1) game
    else {
      val possibleMoves = game.situation.moves.ordered
      val mod           = num % possibleMoves.size
      val move          = possibleMoves(mod.toInt)
      val newNum        = (num - mod) / possibleMoves.size
      steg(newNum, game.apply(move))
    }

  private def unSteg(listMoves: List[Move]): BigInt =
    listMoves.foldLeft(0L: BigInt)((num, move) => {
      val possibleMoves = move.situationBefore.moves.ordered
      val moveIndex     = possibleMoves.indexOf(move)
      (num * possibleMoves.size) + moveIndex
    })

  private def onlyMoves(in: List[MoveOrDrop]): Validated[String, List[Move]] =
    in.partitionMap(identity) match {
      case (moves, Nil) => valid(moves)
      case _            => invalid("Drops not allowed")
    }

  implicit class richMoveMap(moves: Map[Pos, List[Move]]) {
    def ordered: Seq[Move] = moves.values.flatten.toSeq.sortBy(_.toString)
  }
}
