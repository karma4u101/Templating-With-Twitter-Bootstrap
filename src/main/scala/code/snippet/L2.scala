package code
package snippet

import model._
import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._

//Level 2
object L2 extends Loggable {
  logger.info("snippet.L2 obj start")
  lazy val menu = {
    Menu.params[(P1, P2)]("P2", "P2",
                                  {
                                    case P1(p1id) :: 
                                    P2(p2id) :: _ =>
                                      Full((p1id, p2id))
                                    case _ => Empty
                                  },
                                  pp => List(pp._1.id.toString,
                                             pp._2.id.toString)
                                ) / "pt" / * / * 
  }
  logger.info("snippet.L1 obj end")
}

class L2(pp: (P1, P2)) extends Loggable {
  logger.info("snippet.L2 class start")
  def render = { <div>This is param P1 id={pp._1.id} and P2 id={pp._2.id}</div> }
  logger.info("snippet.L2 class end")
}