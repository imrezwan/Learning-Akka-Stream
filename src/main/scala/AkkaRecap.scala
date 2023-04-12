import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Stash}

object AkkaRecap extends App {
  class SimpleActor extends Actor with Stash{
    override def receive: Receive = {
      case "stashThis" =>
        stash()
      case "change handler now" =>
        unstashAll()
        context.become(anotherHandler)
      case "change" => context.become(anotherHandler)
      case message => println(s"I received : $message")
    }

    def anotherHandler: Receive = {
      case message => println(s"I'm from antother handler : $message")
    }

    override def preStart(): Unit = println("I'm starting now")
  }

  val system = ActorSystem("AkkaRecap")
  val actor = system.actorOf(Props[SimpleActor], "simpleactor")
//  actor ! PoisonPill // stopped the actor
//  actor ! "hello akka actor"  // ! is tell method
  actor ! "change"  // ! is tell method
//
//  //actors have different life cycle : started, stoppped , suspended, resumed, restarted
//  actor ! PoisonPill // stopped the actor

  // scheduler
  import scala.concurrent.duration._
  import system.dispatcher
  system.scheduler.scheduleOnce(2.seconds) {
    actor ! "delayed happy birthday"
  }

  // FSM + ask pattern
  import akka.pattern.ask
  import akka.util.Timeout
  implicit val timeout = Timeout(2.seconds)
  val future = actor ? "question" // ? is ask operator

  // pipe pattern
  import akka.pattern.pipe
  val anotherActor = system.actorOf(Props[SimpleActor], "anotheractor")
  future.mapTo[String].pipeTo(anotherActor) // pipe the data

}
