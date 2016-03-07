package modules

import com.google.inject.AbstractModule
import service.DeviceOperationService
import service.DeviceOperationServiceImpl
import service.DeviceServiceImpl
import service.DeviceService
import service.OwnerService
import service.OwnerServiceImpl

/**
 * @author ooshima
 */
class ServiceModule extends AbstractModule{
  def configure() =  {
    bind(classOf[DeviceOperationService]).to(classOf[DeviceOperationServiceImpl])
    bind(classOf[DeviceService]).to(classOf[DeviceServiceImpl])
    bind(classOf[OwnerService]).to(classOf[OwnerServiceImpl])
  } 
    
}