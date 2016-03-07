package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import org.joda.time.format.DateTimeFormat

import com.sonicbrain.m10keyGen.Generate

import dispatch.Future
import javax.inject.Inject
import play.api.data.validation.ValidationError
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsError
import play.api.libs.json.JsPath
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.libs.json.Reads
import play.api.mvc.Action
import play.api.mvc.Controller
import service.DeviceOperationService

/**
 * @author ooshima
 */

case class OpenNumberDto(userId: String, deviceId: String, openDate: String)

class KeyboxServer @Inject() (val messagesApi: MessagesApi, val service: DeviceOperationService) extends Controller with I18nSupport {

  val onlyMobileNumber: Reads[String] =
    Reads.StringReads.filter(ValidationError("200001"))(_.matches("""0[789]0[0-9]{8}"""))

  val deviceNumberValidation: Reads[String] =
    Reads.StringReads.filter(ValidationError("200002"))(_.matches("[0-9]{7}"))

  val onlyDate: Reads[String] =
    Reads.StringReads.filter(ValidationError("200003"))(_.matches("[0-9]{8}"))

  implicit val openNumberReads: Reads[OpenNumberDto] = (
    (JsPath \ "userId").read[String](onlyMobileNumber) and
    (JsPath \ "deviceId").read[String](deviceNumberValidation) and
    (JsPath \ "openDate").read[String](onlyDate))(OpenNumberDto.apply _)

  val oneTimePasswordGenerator = new Generate()
  
  def smsSubmitResult = Action.async { request =>
    Future(Ok(Json.obj()))
  }

  def getOpenNumber = Action.async { request =>
    val jsonBody = request.body.asJson.getOrElse(Json.parse("""{"userId":"","deviceId":"","openDate":""}"""))
    jsonBody.validate[OpenNumberDto] match {
      case success: JsSuccess[OpenNumberDto] => {
        val value = success.get
        val d = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(value.openDate)
        val key = oneTimePasswordGenerator.EncryptChangeKey(d.getYear, d.getMonthOfYear, d.getDayOfMonth, value.deviceId)
        val template = (p: Map[String, String]) => views.txt.smsmessage(p).toString
        val data = service.sendOpenNumber(template, value.userId, value.deviceId, value.openDate, key)
        //httpで取得した番号をSMS送信
        data.map { response =>
          print(response)
          Ok(Json.toJson(Map("responseCd" -> 100000))) 
        }
      }
      case JsError(error) => {
        Future(BadRequest(Json.toJson(Map("responseCd" -> Integer.parseInt(error.head._2.head.message.toString())))))
      }
    }
  }

}