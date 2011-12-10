package code
package model

import net.liftweb._
import common._
import util._
import net.liftweb.squerylrecord.RecordTypeMode._

case class P1(id: Int) extends Loggable {
  logger.debug("model.P1 class id="+id)
}

object P1 extends Loggable {
  logger.debug("model.P1 object start ")
  
  def listAllP1:List[P1] = {
    from(MySchema.p1)(p => select(p)).toList
  }  
  
  def unapply(id: String): Option[P1] = { 
    logger.debug("model.P1 unapply id="+id)
    find(id)
  }
 
  def unapply(o: Any): Option[Int] = o match {
    case o: P1 => {
      logger.debug("model.P1 object unapply(o:Any) some o.id="+o.id)
      Some(o.id)
    }
    case _ => { 
      logger.debug("model.P1 object unapply(o:Any) No match returning None")
      None
    }
  }  

  def find(id: String): Box[P1] = {
    logger.debug("model.P1 object find id="+id)
    val p1 = Helpers.asInt(id).map(P1.apply)
    logger.debug("model.P1 object find found ="+p1.toString())
    p1
  }
  
  logger.debug("model.P1 object end ")
}