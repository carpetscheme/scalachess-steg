package steg

import utest._

object StegTest extends TestSuite {
  val tests: Tests = Tests {
    test("convert") {
      val message = 67232L
      val encoded = Steg.encodeNum(message)
      encoded ==> "e3 Nc6 d4 Nf6"
    }

    test("revert") {
      val simple  = "e3 Nc6 d4 Nf6"
      val decoded = Steg.decodeNum(simple)
      assert(decoded.isValid)
      decoded.getOrElse(null) ==> 67232L
    }

    test("round trip") {
      val raws = List(
        "e3 Nc6 d4 Nf6 c3 e5 dxe5 Nxe5 Bb5 a6 Ba4 b5 Bb3 d5 e4 dxe4 f4 Qxd1+ Kxd1 Nd3 Be3 Ng4 Bd4 Ngf2+ Bxf2 Nxf2+ Ke1 Nxh1 Bd5 Ra7 Bc6+ Kd8 Bxe4 Bd6 g3 Re8 Nd2 f5 Ne2 fxe4 Kf1 e3 Kg2 exd2 Rxh1 Bb7+ Kf2 Bc5+ Kf1 d1=Q#",
        "c4 Nc6 e3 Nf6 h3 Ne4 d3 Nc5 a3 Ne5 d4 d6 dxe5 dxe5 b4 Qxd1+ Kxd1 Ne4 f3 Nf2+ Ke2 Nxh1 Nd2 Ng3+ Ke1 Bf5 Bd3 Bxd3 Rb1 Bxb1 Nxb1 Rd8 Bd2 e6 h4 Be7 Nh3 Bxh4 Nf2 Ke7 Bc3 f6 Nd2 h5 c5 g5 Nc4 Rhg8 Na5 Nh1 Ke2 Nxf2 Be1 Nd3 Nxb7 Bxe1 Nxd8 Rxd8 c6 a5 bxa5 Bxa5 a4 f5 Kd1 Nf4+ Kc2 Rd2+ Kc1 Nxg2 Kb1 Nxe3 Kc1 h4 Kb1 h3 Kc1 h2 Kb1 h1=Q#",
        "d4 Nf6 c4 Nc6 Nc3 e5 Nd5 Nxd5 cxd5 Nxd4 e3 Nf5 e4 Nd4 h4 Qf6 Bg5 Qb6 b3 h6 Bc4 hxg5 h5 Bc5 Ne2 Qa5+ Kf1 d6 Nxd4 Bxd4 Rc1 Qxa2 Rc2 Qa5 Qc1 g4 h6 g3 f3 gxh6 Rxh6 Rxh6 Qxh6 Bf2 Qh8+ Kd7 Qf8 Qe1#",
        "Nc3 c6 Nf3 Na6 b4 Nxb4 Rb1 c5 a3 Nxc2+ Qxc2 b6 Nb5 Ba6 Qa4 Bxb5 Rxb5 Nf6 Bb2 Nd5 Qg4 Nc7 Bxg7 Bxg7 Qxg7 Rf8 Rb3 Ne6 Qxh7 Qb8 Re3 f6 Qg6+ Rf7 g3 Nf8 Qg8 e5 d4 d6 dxc5 Qc7 cxd6 Qc1#",
        "d4 Nf6 Nf3 Nc6 Nbd2 e6 e4 d6 c4 Qe7 Bd3 e5 d5 Nd4 Nxd4 exd4 O-O Bg4 f3 Bd7 Nb3 Qe5 Be2 c5 dxc6 bxc6 Qxd4 Qxd4+ Nxd4 Rb8 b3 Be7 Be3 Bd8 Rfd1 Bb6 Kf2 Ke7 Rd2 Ba5 Rd3 Bb6 Rad1 Rhd8 g4 h6 Bf4 g5 Bg3 h5 h3 h4 Bh2 Rb7 e5 dxe5 Bxe5 Ne8 Kg2 Bc7 Bxc7 Rxc7 Nf5+ Kf8 f4 gxf4 Nxh4 Ng7 Bf3 Ne6 Nf5 Nc5 Rd4 Ne6 Rd6 c5 h4 Ng7 Nxg7 Kxg7 g5 a5 Kf2 Kf8 Bc6 Ke7 Ba4 Bxa4 Rxd8 Bc6 h5 Ke6 h6 Be4 Rh8 Re7 Re1 Kf5 h7 Kg6 Rc8 Kxh7 Rxc5 a4 b4 Kg6 b5 f6 gxf6 Kxf6 b6 a3 Rc7 Rxc7 bxc7 Bb7 Re8 Kf5 c5 Ba6 Ra8 Bb7 Rf8+ Ke5 c6 Ba6 Ra8 Kd6 Rxa6 Kxc7 Kf3 Kb8 Kxf4 Kc8 Ke5 Kc7 Ke6 Kd8 Kd6 Ke8 Ra7 Kf8 c7 Kf7 c8=Q+ Kg6 Qg4+ Kf6 Ra8 Kf7 Qf5+ Kg7 Ra7+ Kg8 Qc8#",
        "e3 Nc6 Nf3 Nf6 Nc3 e6 a3 Bd6 d4 Ng4 h3 Nf6 Bb5 a6 Bxc6 dxc6 e4 Nd7 O-O h5 h4 c5 Bg5 f6 Be3 cxd4 Bxd4 c5 Be3 Qe7 g3 Ne5 Nxe5 Bxe5 Qe2 Bxc3 bxc3 Kf7 Rad1 b5 c4 Rb8 Rd2 Bb7 Rfd1 Bc6 Kg2 Bxe4+ Kf1 Bc6 Bf4 e5 Be3 Qe6 Bxc5 Qh3+ Ke1 Qh1+ Qf1 Qe4+ Re2 Qxc4 Bd6 Rbd8 Bb4 Bf3 Rxd8 Rxd8 Re3 Rd1#",
        "e4 e5 Nf3 Nc6 Nc3 Bb4 Nd5 Nf6 Nxb4 Nxb4 c3 Nc6 Nxe5 Nxe5 d4 Ng6 Bg5 h6 Bxf6 Qxf6 e5 Qe6 Bd3 d6 Qe2 Nf4 Qe4 dxe5 Bb5+ c6 d5 Nxd5 Bc4 O-O O-O b5 Bb3 Bb7 Bc2 Nf4 Qh7#"
      )

      raws.foreach(game => {
        val roundTrip = Steg.decodeNum(game).map(Steg.encodeNum).getOrElse(null)
        game ==> roundTrip
      })
    }

    test("messages") {
      Steg.encode("yoyos") ==> "h4 c5 e4 a6 Rh3 c4 f4 Nf6 Nc3"
      val out = Steg.decode("h4 c5 e4 a6 Rh3 c4 f4 Nf6 Nc3")
      assert(out.isValid)
      out.getOrElse(null) ==> "yoyos"
    }

    test("invalid input: bad notation") {
      val out = Steg.decode("h4 aa")
      assert(out.isInvalid)
    }

    test("invalid input: no drops") {
      val crazyhouse = """
[Event "internet, rated crazyhouse match"]
[Variant "Crazyhouse"]
[Site "ICS: freechess.org 5000"]
[Date "2001.01.02"]
[Time "20:02:??"]
[Round "-"]
[White "RabidWombat"]
[Black "marcusm"]
[Result "1-0"]
[WhiteElo "2540"]
[BlackElo "2235"]
[TimeControl "120+0"]
1. e4 Nf6 2. Nc3 Nc6 3. d4 e6 4. e5 Nd5 5. Nf3 Bb4 6. Qd3 d6 7. Bg5 Qd7 8. exd6
cxd6 9. Nd2 Bxc3 10. bxc3 f5 11. B@h5+ g6 12. P@c4 Nc7 13. Bf3 d5 14. cxd5 exd5
15. Qe3+ P@e4 16. P@d3 N@e6 17. dxe4 dxe4 18. Bxe4 fxe4 19. Nxe4 O-O 20. Nf6+
Rxf6 21. Bxf6 P@g7 22. Be5 Nxe5 23. dxe5 B@f4 24. P@f7+ Qxf7 25. Qe4 P@d2+ 26.
Ke2 B@d5 27. Qb4 N@c1+ 28. Kd1 Bd7 29. R@e7 Qxe7 30. P@f7+ Qxf7 31. N@e7+ Kh8
32. P@e3 Bxe5 33. f4 a5 34. Qa3 P@b4 35. cxb4 Bxa1 36. c4 P@b2 37. cxd5 Qxe7
38. dxe6 Nxe6 39. B@c2 R@e1+ 40. Kxd2 Rxe3 41. Qxe3 Qxb4+ 42. P@c3 Qe7 43. Bc4
N@d6 44. B2d3 Nxc4+ 45. Bxc4 b1=Q 46. Rxc1 R@b2+ 47. N@c2 Rxc2+ 48. Rxc2 N@f1+
49. Bxf1 Qxf1 50. R@f2 B@c1+ 51. Rxc1 Qxc1+ 52. Kxc1 P@b2+ 53. Rxb2 Bxb2+ 54.
Kxb2 B@a3+ 55. Kc2 R@b2+ 56. Kd1
{Black ran out of time} 1-0
"""

      assert(Steg.decode(crazyhouse).isInvalid)
    }

  }
}
