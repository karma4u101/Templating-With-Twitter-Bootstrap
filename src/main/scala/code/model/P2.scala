package code
package model

import net.liftweb._
import common._
import util._
import net.liftweb.squerylrecord.RecordTypeMode._

case class P2(id: Int) extends Loggable {
  logger.debug("model.P2 class id="+id)
}

object P2 extends Loggable {
  logger.debug("model.P2 object start ")

  /*values in this list should in a real example be dependent on p1 */
  def listAllP2:List[P2] = {
    from(MySchema.p2)(p => select(p)).toList
  }  
  
  def unapply(id: String): Option[P2] = {
    logger.debug("model.P2 unapply id="+id)
    find(id)
  }
  
  def unapply(o: Any): Option[Int] = o match {
    case o: P2 => {
      logger.debug("model.P2 object unapply(o:Any) some o.id="+o.id)
      Some(o.id)
    }
    case _ => {
      logger.debug("model.P2 object unapply(o:Any) No match returning None")
      None
    }
  }  
 
  def find(id: String): Box[P2] = {
    logger.debug("model.P2 object find id="+id)
    val p2 = Helpers.asInt(id).map(P2.apply)
    logger.debug("model.P1 object find found ="+p2.toString())
    p2    
  }
  
  logger.debug("model.P2 object end ")
}