import akka.util.Timeout
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import akka.actor.ActorSystem
import Chapter_1.SetRequest
import Chapter_1.AkkademyDb
import akka.testkit.TestActorRef
import scala.concurrent.duration

class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach{

  implicit val system = ActorSystem()
  val actorRef = TestActorRef(new AkkademyDb)
  val akkademyDb = actorRef.underlyingActor
  describe("akkademyDb") {
    describe("given SetRequest"){
      it ("should place key/value into map"){

         actorRef ! SetRequest("key", "value")

         akkademyDb.map.get("key") should equal (Some("value"))

      }
      describe("given a second request"){
        it ("should overwrite the first value by the second value"){
          actorRef ! SetRequest("key", "value2")
          akkademyDb.map.get("key") should equal (Some("value2"))
        }
      }
    }
  }
}

