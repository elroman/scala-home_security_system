package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class ControlPanelController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def controlPanelPage = Action {
    Ok(views.html.control_panel_page("Your new application is ready.")(isActive = false))
  }

  def activateSecurity = Action {
    Ok(views.html.control_panel_page("Your new application is ready.")(isActive = false))
  }

  def deactivateSecurity = Action {
    Ok(views.html.control_panel_page("Your new application is ready.")(isActive = false))
  }

  def takePicture = Action {
    Ok(views.html.control_panel_page("Your new application is ready.")(isActive = false))
  }

  def getSecuritySystemState = Action {
    Ok(views.html.control_panel_page("Your new application is ready.")(isActive = false))
  }

}
