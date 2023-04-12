import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object FirstPrinciples extends App {
  implicit val system = ActorSystem("AkkaRecap")
  implicit val materializer = ActorMaterializer()

  val source = Source(1 to 10)

  val sink = Sink.foreach(println)

  val graph = source.to(sink)

//  graph.run()

  val flow = Flow[Int].map( _ * 10)

  source.via(flow).to(sink).run()
}
