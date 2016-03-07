package service

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import dao.Tables.Owner

/**
 * @author ooshima
 */
trait OwnerService {
  def createPin(name:String , mobileNumber: String): Future[Option[String]]
  def updateValid(mobileNumber:String , pinNumber:String) : Future[Boolean]
}

class OwnerServiceImpl @Inject() (val dbConfigProvider: DatabaseConfigProvider) extends OwnerService {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig.driver.api._

  def createPin(name:String, mobileNumber: String): Future[Option[String]] = {
    hasAvailablePin(mobileNumber).map { h =>
      h match {
        case true => {
          None
        }
        case false => {
          val pin = "%05d".format(new Random(new java.security.SecureRandom).nextInt(100000))
          dbConfig.db.run(Owner.map { v => (v.name , v.mobileNumber, v.pin) } += (name , mobileNumber, pin))
          Some(pin)
        }
      }
    }
  }
  
  def updateValid(mobileNumber:String , pinNumber:String) : Future[Boolean] = { 
    val update = Owner.filter{x => x.mobileNumber === mobileNumber && x.pin === pinNumber}.map { x => (x.validatedAt)}.update(Some(new java.sql.Timestamp(new java.util.Date().getTime)))
    dbConfig.db.run(update).map(_ == 1)
  }

  def hasAvailablePin(mobileNumber: String): Future[Boolean] = {
    val now = new java.util.Date();
    dbConfig.db.run(Owner.filter(x => x.mobileNumber === mobileNumber).result).map { mobileNumbers =>
      mobileNumbers.exists { x => x.validatedAt != None || x.createdAt.map { x => x.getTime <= now.getTime + 1800000 }.getOrElse(false) }
    }
  }

}