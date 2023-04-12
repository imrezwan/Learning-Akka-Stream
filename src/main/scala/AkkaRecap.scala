import akka.actor.{Actor, ActorSystem}


object AkkaRecap extends App {
  class SimpleActor extends Actor {
    override def receive: Receive = {
      case message => println(s"I received : $message")
    }
  }

  val system = ActorSystem("AkkaRecap")
  new SimpleActor
}
