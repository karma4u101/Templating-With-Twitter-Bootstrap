package code
package snippet

import model._
import lib.util._
import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._

object Countries extends Loggable {
  logger.info("snippet.Countries obj start")
  
  val ccList:List[Country]  = Country.getAllList()
  
  lazy val menu = Menu.i("Countries") / "countries"

  def render = { "a"        #> ccList.map(
            c => "* [href]" #> ACountry.menu.calcHref(c) & 
                 "* *" #> (c.name)               
        )
  }
  
  logger.info("snippet.Countries obj stop")
}
