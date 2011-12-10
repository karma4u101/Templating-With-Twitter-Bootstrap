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

class Region private() extends Record[Region] with KeyedRecord[Long] with Loggable {
  
  def meta = Region
  
  @Column(name="id")
  override val idField = new LongField(this)
  val cid = new IntField(this)
  val seoUrl = new StringField(this,50)
  val name = new StringField(this,50)
}

object Region extends Region with MetaRecord[Region] with Loggable {
  logger.info("model.Region obj start")
  
  def getAllList():List[Region] = transaction {
    from(MySchema.region)(r => select(r)).toList
  } 
  
  def find(id: String): Box[Region] = {
    logger.info("model.Region:find(id="+id+":String)")
    val r = getAllList().find(_.seoUrl._1==id)
    logger.info("model.Region:find found "+r.toString())
    r
  }

  def unapply(id: String): Option[Region] = {
    logger.info("model.Region:unapply(id="+id+":String) returns Option[Country]")
    find(id)
  }

  logger.info("model.Region obj end")
}
