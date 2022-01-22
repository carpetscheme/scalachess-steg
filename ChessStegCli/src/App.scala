import cats.data.Validated.{Invalid, Valid}
import cats.implicits._
import com.monovore.decline._
import steg.Steg

object App
    extends CommandApp(
      name = "scalachess-steg",
      header = "Hiding messages in chess games!",
      main = {
        val input  = Opts.argument[String]("Message or chess game.")
        val steg   = Opts.flag("steg", help = "[Default] Encode message as chess game.").orNone
        val unSteg = Opts.flag("unsteg", help = "Decode input from chess game.").orFalse

        (input, steg, unSteg).mapN { (input, _, unSteg) =>
          if (unSteg) Steg.decode(input) match {
            case Valid(message) => println(message)
            case Invalid(error) => println(s"Invalid input: $error")
          }
          else println(Steg.encode(input))
        }
      }
    )
