package actor

import javax.inject.Singleton

import actor.proto.FactoryObject.ActivationMessage
import akka.actor.Actor

@Singleton
class MotionSensorActor extends Actor {
  private var activeSys = false
  /*val gpio: GpioController = GpioFactory.getInstance
  val input: GpioPinDigitalInput = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, "MyInput")
*/


  def receive = {
    case ActivationMessage(active) => activateWork(active)
  }

  def activateWork(active: Boolean) = if (active) {
    println("MotionSensorActor: activateWork!!!")
  } else {
    println("MotionSensorActor: deactivateWork!!!")
  }

}
