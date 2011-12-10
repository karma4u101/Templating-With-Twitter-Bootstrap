package code
package model

import net.liftweb._
import common._
import util._
import org.squeryl.annotations.Column
import net.liftweb.record._
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._


class Country private() extends Record[Country] with KeyedRecord[Long] with Loggable  {
  
  def meta = Country
  
  @Column(name="id")
  override val idField = new LongField(this)	
  val seoUrl = new StringField(this,50)
  val name = new StringField(this,50)
}

object Country extends Country with MetaRecord[Country] with Loggable {
  logger.debug("model.Contry obj start")
  
  def getAllList():List[Country] = transaction {
    from(MySchema.country)(c => select(c)).toList
  } 
  
  def find(id: String): Box[Country] = {
    logger.debug("model.Country object find id(string)="+id)
    val c = getAllList.find(_.seoUrl._1==id)
    logger.debug("model.Country object find found ="+c.toString())
    c
  }

  def unapply(id: String): Option[Country] = {
    logger.debug("model.Country:unapply(id="+id+":String) returns Option[Country]")
    find(id)
  }
 
  logger.debug("model.Contry obj end")
}


