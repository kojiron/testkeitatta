package service

import dao.Tables.DevicetypeRow
import dao.Tables.Devicetype
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future
import dao.Tables.Device
import scala.concurrent.ExecutionContext.Implicits.global
import dao.Tables.DeviceRow

/**
 * @author ooshima
 */

case class DeviceDto(id:Option[Int] , deviceType:Int , uniqueId:String , subId:Option[String] , ownerId:Option[Long] , name:String)
trait DeviceService {
  def listDeviceType():Future[Seq[DevicetypeRow]]
  def createDeviceType(id:Int , name:String):Future[Int]
  def createDevice(device:DeviceDto , deviceType:Int):Future[Int]
  def removeDeviceType(id:Array[Int]):Future[Int]
  def updateDeviceType(id:Int ,id_old:Int , name:String):Future[Int]
}


class DeviceServiceImpl @Inject() (val dbConfigProvider: DatabaseConfigProvider) extends DeviceService {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.driver.api._
  
  def listDeviceType():Future[Seq[DevicetypeRow]] = {
    dbConfig.db.run(Devicetype.sortBy(_.id).result)
  }
  


  def createDeviceType(id:Int , name:String):Future[Int] = {
    dbConfig.db.run(Devicetype += DevicetypeRow(id , name))
  }


//  def removeDeviceType(id:Array[Int]):Array[Future[Int]] = {

  def removeDeviceType(id:Array[Int]):Future[Int] = {

//    for(ids <- id){
//      dbConfig.db.run(Devicetype.filter(p => p.id === ids).delete)
//    }
//      id.foreach{ case ids => dbConfig.db.run(Devicetype.filter(p => p.id === ids).delete) }

      id.map{ (ids:Int) => dbConfig.db.run(Devicetype.filter(p => p.id === ids).delete) }


//      dbConfig.db.run(Devicetype.filter(p => p.id === id(1)).delete)   <==== OK!!
      Future {1}


  }

//type mismatch;
// found   : Array[scala.concurrent.Future[Int]]
// required: scala.concurrent.Future[Array[Int]]

  def createDevice(device:DeviceDto , deviceType:Int):Future[Int] = {
    val query = 
      Device.map(d => (d.uniqueId , d.subId , d.ownerId , d.`type`));
    val action = query += (device.uniqueId , device.subId.getOrElse("") , device.ownerId.getOrElse(0) , deviceType)
    dbConfig.db.run(action)
  }


  def updateDeviceType(id:Int ,id_old:Int , name:String):Future[Int] = {
    dbConfig.db.run(Devicetype.filter(p => p.id === id_old).map(p => (p.id , p.name)).update(id , name))
  }

}