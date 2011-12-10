package code
package snippet

import model._
import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._


//Level 1 
object L1 extends Loggable {
  logger.info("snippet.L1 obj start")
  
  lazy val menu = { Menu.param[P1]("P1", "P1",
      P1.find, _.id.toString) / "pt" / * 
    }
  logger.info("snippet.L1 obj end")
}

class L1(p1:P1) extends Loggable {
  logger.info("snippet.L1 class start p1="+p1.toString())
  
  def m: Loc[(P1, P2)] = L2.menu
  
  def render = { "a" #> P2.listAllP2.map(t => 
    "* [href]" #> L2.menu.calcHref((p1, t)) &
    "* *+" #> t.id.toString)
  }    

  logger.info("snippet.L1 class end")
}