package code
package snippet

import model._
import lib.util._
import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._
import scala.xml.Text

object ACountry extends Loggable {
  logger.info("snippet.ACountry obj start")
 
  lazy val menu = { Menu.param[Country]("Country", Loc.LinkText(c => Text( (c.name._1).toString() )), 
                                    Country.find, _.seoUrl._1) / "countries" / * 
                                    }
                                 
  logger.info("snippet.ACountry obj end")                                  
}

class ACountry(country: Country) extends Loggable {
  logger.info("snippet.ACountry class start")

  val reList:List[Region] = Region.getAllList().filter(r => r.cid._1==country.id)
  
  def m: Loc[(Country, Region)] = ARegion.menu
  def render = { 
    "h3 *"     #> country.name &
    "a"        #> reList.map(r => 
    "* [href]" #>  ARegion.menu.calcHref((country, r)) &
    "* *"      #> (r.name) )     
    } 
  
  logger.info("snippet.ACountry class end")
}

