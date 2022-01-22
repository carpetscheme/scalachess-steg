package steg

import utest._

object AppTest extends TestSuite{
  val tests: Tests = Tests{
    test("Number of opening moves"){
      val game = chess.Setup(chess.variant.Standard)
      game.situation.moves.values.flatten.size ==> 20
    }
  }
}
