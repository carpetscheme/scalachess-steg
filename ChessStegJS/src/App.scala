import cats.data.Validated
import com.raquo.laminar.api.L._
import org.scalajs.dom
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.{document, window}
import steg.Steg

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js


object App {
  def main(args: Array[String]): Unit = {
    document.addEventListener("DOMContentLoaded", (_: dom.Event) => setupUI())
  }

  def setupUI(): Unit = {

    case class FormState(data: String = "", steg: Boolean = true)
    val stateVar = Var(FormState())
    val checkedWriter = stateVar.updater[Boolean]((state, checked) => state.copy(steg = checked))
    val dataWriter = stateVar.updater[String]((state, data) => state.copy(data = data))

    val output = pre(
      child <-- stateVar.signal.map {
        case FormState(d, _) if d.isEmpty => code()
        case FormState(d, true) => code(Steg.encode(d))
        case FormState(d, false) => Steg.decode(d) match {
          case Validated.Valid(a) => code(a)
          case Validated.Invalid(e) => mark(e)
        }
      })

    val app = form(
      onSubmit
        .preventDefault
        .mapTo(output.ref.textContent) --> (pgn => viewOnLichess(pgn)),
      p(
        input(
          typ("checkbox"),
          defaultChecked(true),
          onInput.mapToChecked --> checkedWriter
        ),
        label(
          child <-- stateVar.signal.map {
            case FormState(_, true) => span(b("Steg"), " / " + "Unsteg")
            case FormState(_, false) => span("Steg" + " / ", b("Unsteg"))
          },
          marginLeft("5px")
        )
      ),
      textArea(
        onMountFocus,
        placeholder("Message or PGN..."),
        rows(4),
        minLength(1),
        onInput.mapToValue --> dataWriter
      ),
      output,
      button(
        typ("submit"),
        disabled <-- stateVar.signal.map {
          case FormState(_, false) => true
          case FormState(d, true) if d.isEmpty => true
          case _ => false
        },
        "View game on lichess"
      )
    )

    val containerNode = dom.document.querySelector("#article")
    render(containerNode, app)
  }

  def viewOnLichess(in: String): Unit =
    Ajax.post(
      url = "https://lichess.org/api/import",
      timeout = 12000,
      data = s"pgn=$in",
      headers = Map("Content-Type" -> "application/x-www-form-urlencoded")
    ).foreach(xhr =>
      window.open(js.JSON.parse(xhr.responseText).url.toString)
    )

}

