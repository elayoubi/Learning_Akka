package Chapter_1

import akka.actor.Actor
import akka.event.Logging

import scala.collection.mutable


case class SetRequest(key: String, value: Object)


class AkkademyDb extends Actor {
  val map = new mutable.LinkedHashMap[String, Object]()
  val log = Logging(context.system, this)
  override def receive : Receive = {
    case SetRequest(k,v) => {
      map.put(k,v)
      log.info("received SetRequest key={} value={}", k, v)
    }
    case o => log.info("received unknown message : {}", o)
  }
}