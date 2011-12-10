package code
package model

import lib.util._

object DemoData {

  def createDemoData {
    getCountryList().foreach(MySchema.country.insert(_))
    getACountryRegionList().foreach(MySchema.region.insert(_))
    p1List().foreach(MySchema.p1.insert(_))
    p2List(null).foreach(MySchema.p2.insert(_))
  }
  
  private def p1List():List[P1]=List(P1(1),P1(2),P1(3))
  private def p2List(p1:P1):List[P2]=List(P2(1),P2(2),P2(3))

  private def getCountryList():List[Country] = List(
      Country.createRecord.idField(1).seoUrl(SEOTransform.toSEOFriendly("UNITED KINGDOM")).name("United Kingdom"),
      Country.createRecord.idField(2).seoUrl(SEOTransform.toSEOFriendly("UNITED STATES")).name("United States"),
      Country.createRecord.idField(3).seoUrl(SEOTransform.toSEOFriendly("COSTA RICA")).name("Costa Rica"),
      Country.createRecord.idField(4).seoUrl(SEOTransform.toSEOFriendly("MYANMAR (Burma)")).name("Myanmar (Burma)"),
      Country.createRecord.idField(5).seoUrl(SEOTransform.toSEOFriendly("PAPUA NEW GUINEA")).name("Papua New Guinea"),
      Country.createRecord.idField(6).seoUrl(SEOTransform.toSEOFriendly("PUERTO RICO")).name("Puerto Rico")
      )

  private def getACountryRegionList():List[Region] = List(
      Region.createRecord.idField(1).cid(1).seoUrl(SEOTransform.toSEOFriendly("London")).name("London"),
      Region.createRecord.idField(2).cid(1).seoUrl(SEOTransform.toSEOFriendly("Scotland")).name("Scotland"),
      Region.createRecord.idField(3).cid(1).seoUrl(SEOTransform.toSEOFriendly("Northern Ireland")).name("Northern Ireland"),
      Region.createRecord.idField(4).cid(2).seoUrl(SEOTransform.toSEOFriendly("New York")).name("New York"),
      Region.createRecord.idField(5).cid(2).seoUrl(SEOTransform.toSEOFriendly("Rhode Island")).name("Rhode Island"),
      Region.createRecord.idField(6).cid(2).seoUrl(SEOTransform.toSEOFriendly("San Francisco Bay Area")).name("San Francisco Bay Area"),
      Region.createRecord.idField(7).cid(3).seoUrl(SEOTransform.toSEOFriendly("Southern Pacific Zone")).name("Southern Pacific Zone"),
      Region.createRecord.idField(8).cid(3).seoUrl(SEOTransform.toSEOFriendly("Central Zone")).name("Central Zone"),
      Region.createRecord.idField(9).cid(3).seoUrl(SEOTransform.toSEOFriendly("Northern Zone and Arenal")).name("Northern Zone and Arenal"),      
      Region.createRecord.idField(10).cid(4).seoUrl(SEOTransform.toSEOFriendly("Mandalay Region‎")).name("Mandalay Region‎"),
      Region.createRecord.idField(11).cid(4).seoUrl(SEOTransform.toSEOFriendly("Ayeyarwady Region")).name("Ayeyarwady Region"),
      Region.createRecord.idField(12).cid(4).seoUrl(SEOTransform.toSEOFriendly("Sagaing Region")).name("Sagaing Region"),
      Region.createRecord.idField(13).cid(5).seoUrl(SEOTransform.toSEOFriendly("Highlands Region")).name("Highlands Region"),      
      Region.createRecord.idField(14).cid(5).seoUrl(SEOTransform.toSEOFriendly("Islands Region")).name("Islands Region"),
      Region.createRecord.idField(15).cid(5).seoUrl(SEOTransform.toSEOFriendly("Papua Region")).name("Papua Region"),
      Region.createRecord.idField(16).cid(6).seoUrl(SEOTransform.toSEOFriendly("6 Region 1")).name("6 Region 1"),
      Region.createRecord.idField(17).cid(6).seoUrl(SEOTransform.toSEOFriendly("6 Region 2")).name("6 Region 2"),
      Region.createRecord.idField(17).cid(6).seoUrl(SEOTransform.toSEOFriendly("6 Region 3")).name("6 Region 3")
      )

}