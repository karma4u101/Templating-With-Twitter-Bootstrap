package code
package snippet

import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._
import model._


//Level 0 
//The starting point snippet for list of P1 menu params 
object L0 extends Loggable {
  logger.info("snippet.L0 obj start")
  
  lazy val menu = Menu.i("ParamTest") / "pt"
  
  
  def render = { "a" #> P1.listAllP1.map(
      p1 => "* [href]" #> L1.menu.calcHref(p1) & "* *+" #> p1.id)
  }  
  
  logger.info("snippet.L0 obj end")
}