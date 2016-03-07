package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.libs.json.Json
import scala.util.Random
import javax.inject.Inject
import service.OwnerService

/**
 * @author ooshima
 */

case class Owner(name: String, mobileNumber: String)

class OwnerController @Inject() (val ownerService: OwnerService) extends Controller {

  implicit val ownerReader = Json.reads[Owner]

  def regist = Action.async {
    Future(Ok(views.html.owner.index()))
  }

  val mobileNumberMatch = """(0[9,8,7]0[0-9]{8})""".r

  def sendSms(mobileNumber: String) = Action.async { request =>
    request.body.asJson.map { j =>
      println(j)
      val o = j.as[Owner]
      o.mobileNumber match {
        case mobileNumberMatch(v) =>
          ownerService.createPin(o.name, v).map { pin =>
            pin match {
              case Some(p) => Ok(Json.obj("pin" -> p))
              case None    => BadRequest(Json.obj())
            }
          }
        case _ =>
          Future(BadRequest(Json.obj("code" -> "Already exist")))
      }
    }.getOrElse(Future(BadRequest(Json.obj("code" -> "JSON Error"))))
  }

  def validate(mobileNumber: String, pinNumber: String) = Action.async {
    ownerService.updateValid(mobileNumber, pinNumber).map { x =>
      x match {
        case true => {
          Ok(Json.obj())
        }
        case false => {
          BadRequest(Json.obj())
        }
      }
    }
  }
}