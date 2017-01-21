package fsm
import akka.actor.{ ActorRef, FSM }
import akka.actor.Actor
import scala.concurrent.duration._


sealed trait State
case object PingState extends State
case object PongState extends State
case object InitState extends State

sealed trait Data
case object Void extends Data
case object Full extends Data

case object PingMessage
case object PongMessage
case object Start

class PingPongFsm extends FSM[State,Data] with Actor {
  
  startWith(InitState, Void)
  
  when(InitState)
  {
    case Event(Start,Void) => self ! PongMessage
                              log.info("Started goto Ping State")
                              goto(PingState) using(Full)
    case _ => stay() 
  }
  
  when(PingState)
  {
    case Event(PongMessage,Full) => self ! PingMessage
                                    log.info("Pong! - goto Pong State")
                                    goto(PongState)
    case _ => stay()
  }
    when(PongState)
  {
    case Event(PingMessage,Full) => self ! PongMessage
                                    log.info("Ping! - goto Ping State")
                                    goto(PingState)
    case _ => stay()
  }
  
}