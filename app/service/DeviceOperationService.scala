package service

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import com.ning.http.client.filter.FilterContext
import com.ning.http.client.filter.ResponseFilter
import dispatch._
import com.typesafe.config.ConfigFactory
import play.api.Configuration

/**
 * @author ooshima
 */
trait DeviceOperationService {
  def sendOpenNumber(f:(Map[String,String]=>String) , userId:String , deviceId:String , openDate:String , key:String):Future[String]
}

class DeviceOperationServiceImpl extends DeviceOperationService {

  val MyHttp = Http.configure { builder =>  
      builder.addResponseFilter(new ResponseFilter {
        override def filter[T](context:FilterContext[T]) = {
          context.getResponseHeaders.getHeaders.get("Content-Type").asScala.toList match {
            case "text/html" :: Nil =>
              context.getResponseHeaders.getHeaders.put("Content-Type" , List("text/html; charset=utf-8").asJava)
            case _ => ()
          }
          context
        }
      })
  }
  
  val config = Configuration(ConfigFactory.load())
  val id = config.getString("sms.id").getOrElse("")
  val password = config.getString("sms.password").getOrElse("")
  val smsStop = config.getString("sms.stop").getOrElse("true")
  val svc = url(config.getString("sms.request_url").getOrElse("http://onepi2.mbgabot.info/cgi-bin/test_2.cgi"))
  
  def sendOpenNumber(f:(Map[String,String] => String) , userId:String , deviceId:String , openDate:String , key:String):Future[String] = {
    val parameters = Map("userId"->userId , "deviceId"->deviceId , "openDate" -> openDate , "key" ->key)
    sendSms(f(parameters) , userId)
  }
  
  def sendSms(template:String , telNo:String):Future[String] = {
    val mobileNoWithCountry = "81" + telNo.substring(1)
    def myPostWithParams = svc << Map("telno" -> mobileNoWithCountry ,  "id" -> id , "pass" -> password , "text" -> template)
    smsStop match {
      case "false" => {
        MyHttp(myPostWithParams OK as.String)
      }
      case _ => { 
        println(template)
        Future("")
      }
    }
  }
  
}