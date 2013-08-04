package code
package comet

import java.text.DateFormat._
import net.liftweb._
import http._
import SHtml._ 
import scala.xml.Text
import net.liftweb.common.{Box, Full}
import net.liftweb.util._
import net.liftweb.actor._
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.{SetHtml}
import net.liftweb.http.js.JE.Str

class CometMessage extends CometActor {
        
  override def defaultPrefix = Full("comet")
                
  //def render = bind("message" -> <span id="message">Whatever you feel like returning</span>)
  
  def render = "#message *" replaceWith (getDateTimeInstance format now) 
  
  Schedule.schedule(this, Message, 10000L)
                
  override def lowPriority : PartialFunction[Any,Unit] = {
    case Message => {
      partialUpdate(SetHtml("message", Text("updated: " + now.toString)))
      Schedule.schedule(this, Message, 10000L)
    }
  }
}
case object Message