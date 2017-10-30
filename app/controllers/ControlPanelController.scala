package controllers

import javax.inject._

import actor.proto.FactoryObject.{ActivationMessage, GetStateReq, GetStateRes, TakePhotoMessage}
import akka.actor.ActorRef
import akka.util.Timeout
import play.api.mvc._
import akka.pattern.ask

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._


@Singleton
class ControlPanelController @Inject()(@Named("securitySystem") securitySystem: ActorRef, cc: ControllerComponents)
                                      (implicit ec: ExecutionContext) extends AbstractController(cc) {
  implicit val timeout: Timeout = 5.seconds

  def controlPanelPage = Action.async {
    isSystemActivate.map(isActive=> Ok(views.html.control_panel_page("Your new application is ready.")(isActive)))
  }

  def activateSecurity = Action.async {
    securitySystem ! ActivationMessage(true)
    isSystemActivate.map(isActive=> Ok(views.html.control_panel_page("Your new application is ready.")(isActive)))
  }

  def deactivateSecurity = Action.async {
    securitySystem ! ActivationMessage(false)
    isSystemActivate.map(isActive=> Ok(views.html.control_panel_page("Your new application is ready.")(isActive)))
  }

  def takePicture = Action {
    Ok(views.html.control_panel_page("take Picture")(isActive = false))
  }

  private def isSystemActivate: Future[Boolean] = {
    (securitySystem ? GetStateReq).mapTo[GetStateRes].map{
      message => message.active
    }
  }

}
