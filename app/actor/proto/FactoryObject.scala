package actor.proto

object FactoryObject {

  case class ActivationMessage(active: Boolean)

  case class GetStateRes(active: Boolean)

  case object GetStateReq

  case object ReadMotionSensorMessage

  case object TakePhotoMessage

  case object DetectedMoveEvt

}
