package actor

import java.io.{BufferedReader, File, IOException, InputStreamReader}
import java.util.Date

import actor.proto.FactoryObject.TakePhotoMessage
import akka.actor.Actor
import models.Picture
import play.Logger
import services.DateUtil


class CameraActor extends Actor {

  private val PATH_TO_FOLDER = context.system.settings.config.getString("config.pathToFolder")
  private val DEVICE = context.system.settings.config.getString("config.camera_device")
  private val PICTURE_SIZE = "640x480"
  private val QUALITY = 30

  def receive = {
    case TakePhotoMessage => makeSeriaPhotos
  }

  def makeSeriaPhotos = {
    for (i <- 1 to 2) takePhoto
  }

  def takePhoto = {
    val photoTime = new Date
    val fullPath = geFullPhotoPath(photoTime)

    val photo = new Picture(DEVICE, PICTURE_SIZE, fullPath, DateUtil.getStringFromDateTime(photoTime), QUALITY)
    val command = photo.getCommandForShot

    Logger.debug("CameraActor: photo:", photo.nameFile)

    executeCommand(command)
  }

  private def geFullPhotoPath(photoTime: Date): String = {
    val pref = if (PATH_TO_FOLDER.substring(PATH_TO_FOLDER.length - 1) == "/") ""
    else "/"
    val theDir = new File(PATH_TO_FOLDER + pref + DateUtil.getStringFromDate(photoTime))
    if (theDir.exists) return theDir.toString
    try {
      theDir.mkdir
      theDir.toString
    } catch {
      case se: SecurityException =>
        Logger.error("Problem created new folder! ", se)
        PATH_TO_FOLDER
    }
  }

  private def executeCommand(cmd: String): Unit = {
    val rt = Runtime.getRuntime
    try {
      val process = rt.exec(cmd)
      val bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream))

      Stream.continually(bufferedReader.readLine()).takeWhile(_ != null).foreach(elem=>println(elem))

    } catch {
      case e: IOException =>
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
  }
}