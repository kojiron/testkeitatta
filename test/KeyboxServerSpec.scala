import org.junit.runner._
import org.specs2.runner.JUnitRunner
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._


/**
 * @author ooshima
 */
@RunWith(classOf[JUnitRunner])
class KeyboxServerSpec extends Specification{
  "KeyboxServer" should {
    "sendOpenNumber request" in new WithApplication {
      val req = FakeRequest(POST , "/sendOpenNumber").withFormUrlEncodedBody("userId"->"09011111111" , "deviceId"->"1", "openDate"->"2015-09-01 00:00:00")
      route(req) must beSome.which (status(_) == OK)
    }
    
    "sendOpenNumber device id Fail" in new WithApplication {
      val req = FakeRequest(POST , "/sendOpenNumber").withFormUrlEncodedBody("userId"->"09011111111" , "openDate"->"2015-09-01 00:00:00")
      route(req) must beSome.which (status(_) == BAD_REQUEST)
    }
    
  }  
}