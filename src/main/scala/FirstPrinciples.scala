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

//  source.via(flow).to(sink).run()

  // create a stream that takes the names of persons, then you will keep the first 2 names with length > 5 char

  val personsSource = Source(List("Rezwan", "Robin", "Rana", "Sheldon", "Walter"))
  val personsFlow = Flow[String].filter(_.length > 5)
  val limitFlow = Flow[String].take(2)
  val personsSink = Sink.foreach(println)

  personsSource.via(personsFlow).via(limitFlow).to(personsSink).run();


}
