package controllers

import service.DeviceService
import javax.inject.Inject
import play.api.mvc.Controller
import play.api.mvc.Action
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import dao.Tables.DevicetypeRow
import dao.Tables.DeviceRow
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * @author ooshima
 */
class DeviceController  @Inject()(val deviceService: DeviceService) extends Controller{
  
  //DeviceRow(id: Long, `type`: Int, uniqueId: String, subId: String, ownerId: Long, createdAt: java.sql.Timestamp)

  
  implicit val deviceRowReader = (
    (__ \ "id").read[Long] and
    (__ \ "type").read[Int] and
    (__ \ "uniqueId").read[String] and
    (__ \ "subId").readNullable[String] and
    (__ \ "ownerId").read[Long] and
    (__ \ "name").read[String]
    tupled
  )
  
  
  def list = Action.async {
    Future(Ok(views.html.index()))    
  }
  
  def create = Action.async { implicit request =>
    val t = deviceRowReader.reads(request.body.asJson.get).get
    val deviceRow:DeviceRow = null
    /*
    deviceService.createDevice(deviceRow,1).map{ l => 
      Ok(Json.obj())
    }
    */
    Future{Ok(Json.obj())}
  }
  
}