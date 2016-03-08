package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import javax.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import scala.concurrent.Future
import dao.Tables._
import play.api.libs.json.Reads
import service.DeviceService

/**
 * @author ooshima
 */

class DevicetypeController @Inject()(val deviceService: DeviceService) extends Controller{
  
  def list = Action.async {
    deviceService.listDeviceType().map { l =>
      Ok(views.html.device.list_devicetype(l))  
    }
  }
  
  def create = Action.async { implicit request =>
    request.body.asJson.map{ j =>
      val deviceType = (j \ "name").asOpt[String]
      val id = (j \ "id").asOpt[Int]
      (id , deviceType) match {
        case (Some(i) , Some(d)) => {
          deviceService.createDeviceType(i , d).map{ id => 
            Ok(Json.obj())
          }
        }
        case _ => Future(BadRequest(Json.obj()))
      }
    }.getOrElse(Future{BadRequest(Json.obj())})
  }

  def remove = Action.async { implicit request =>
    request.body.asJson.map{ j =>
//      val deviceType = (j \ "name").asOpt[String]
//      val deviceType = (j \ "id").asOpt[String]
//      val deviceType = ("test").asOpt[String]

      //javascript array no mama toridasitai
      val id = (j \ "id").asOpt[Array[Int]]
//      val id = (j \ "id").asOpt[Int]
      (id) match {
        case (Some(i)) => {
          deviceService.removeDeviceType(i).map{ id =>
            Ok(Json.obj())
 
          }
        }
        case _ => Future(BadRequest(Json.obj()))
      }
    }.getOrElse(Future{BadRequest(Json.obj())})
  }

}