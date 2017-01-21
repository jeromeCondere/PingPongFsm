import akka.actor.{ActorSystem,Props}
import fsm.PingPongFsm
import fsm.Start
    



object Main extends App {
  println("Hello World!")
  
    val system = ActorSystem("system")
    val pingPongFSM = system.actorOf(Props[PingPongFsm], "PingPong")
    pingPongFSM ! Start
    
}
