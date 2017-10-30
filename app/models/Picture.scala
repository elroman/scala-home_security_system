package models

class Picture(systemDevice: String, pictureSize: String, pictureDir: String, date: String, quality: Int) {
  val nameFile = "pict_" + date.replace(" ", "_") + ".jpg"

  def getCommandForShot: String = {

    val sb = new StringBuffer("fswebcam")
      .append(" -d ")
      .append(systemDevice)
      .append(" -r ")
      .append(pictureSize)
      .append(" ")
      .append(pictureDir)
      .append(if (pictureDir.substring(pictureDir.length - 1) == "/") "" else "/")
      .append(nameFile).append(" -S ").append(quality)

    sb.toString
  }
}