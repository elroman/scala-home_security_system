package actor

import javax.inject.{Inject, Singleton, _}

import actor.proto.FactoryObject._
import akka.actor.{Actor, ActorRef}


@Singleton
class SecuritySystemActor @Inject()(@Named("motionSensor") motionSensor: ActorRef,
                                    @Named("cameraActor") cameraActor: ActorRef
                                   ) extends Actor {
  private var activeSys = false

  override def receive = {
    case ActivationMessage(active) => activeSys = active; motionSensor ! ActivationMessage(active)
    case DetectedMoveEvt => cameraActor ! TakePhotoMessage
    case GetStateReq => sender() ! GetStateRes(activeSys)

  }
}