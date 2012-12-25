package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import mapper._
import net.liftweb.squerylrecord.RecordTypeMode._

import code.model._
import code.snippet._

//import org.hoisted.lib._
//import java.io._

import net.liftmodules.{FoBo,JQueryModule}

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {

//    RunHoisted(new File("/usr/local/proj/cms_site"),new File("/home/peter/test"))
    
    // where to search snippet
    LiftRules.addToPackages("code")
    
    //We skip the FoBo built in JQuery in favor for the FoBo included lift-jquery-module
    //FoBo.InitParam.JQuery=FoBo.JQuery172  
    FoBo.InitParam.ToolKit=FoBo.Bootstrap222
    FoBo.InitParam.ToolKit=FoBo.PrettifyJun2011
    FoBo.init()
    //Just to show you do not need to use the FoBo jquery
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery182
    JQueryModule.init() 
 
    /*un-comment and switch to db of your liking */
    MySchemaHelper.initSquerylRecordWithInMemoryDB
    //MySchemaHelper.initSquerylRecordWithMySqlDB
    //MySchemaHelper.initSquerylRecordWithPostgresDB

    Props.mode match {
      case Props.RunModes.Development => {
        logger.info("RunMode is DEVELOPMENT")
        /*OBS! do no use this in a production env*/
        if (Props.getBool("db.schemify", false)) {
          MySchemaHelper.dropAndCreateSchema
        }
        // pass paths that start with 'console' to be processed by the H2Console servlet
        if (MySchemaHelper.isUsingH2Driver) {
          /* make db console browser-accessible in dev mode at /console 
           * see http://www.h2database.com/html/tutorial.html#tutorial_starting_h2_console 
           * Embedded Mode JDBC URL: jdbc:h2:mem:test User Name:test Password:test */
          logger.info("Set up H2 db console at /console ")
          LiftRules.liftRequest.append({
            case r if (r.path.partPath match { case "console" :: _ => true case _ => false }) => false
          })
        }
      }
      case Props.RunModes.Production => logger.info("RunMode is PRODUCTION")
      case _                         => logger.info("RunMode is TEST, PILOT or STAGING")
    }

    // Build SiteMap
//    def sitemap = SiteMap(
//      Menu.i("Home") / "index",
//      code.snippet.Countries.menu,
//      ACountry.menu,
//      ARegion.menu,
//      L0.menu,
//      L1.menu,
//      L2.menu,
//      Menu.i("A1") / "a1" >> Hidden >> LocGroup("bdd"),
//      Menu.i("A2") / "a2" >> Hidden >> LocGroup("bdd"),
//      Menu.i("A3") / "a3" >> Hidden >> LocGroup("bdd"),
//
//      Menu.i("Level 1.1.1") / "page111" >> Hidden >> LocGroup("bdd11"),
//      Menu.i("Level 1.1.2") / "page112" >> Hidden >> LocGroup("bdd11"),
//      Menu.i("Level 1.1.3") / "page113" >> Hidden >> LocGroup("bdd11"),   
//      Menu.i("Level 1.1.4") / "page114" >> Hidden >> LocGroup("bdd11"),
//      
//      Menu.i("Level 1.2.1") / "page121" >> Hidden >> LocGroup("bdd12"),
//      Menu.i("Level 1.2.2") / "page122" >> Hidden >> LocGroup("bdd12"),
//    
//      Menu.i("Level 1.3") / "page13" >> Hidden >> LocGroup("bdd1"),
//      Menu.i("Level 1.4") / "page14" >> Hidden >> LocGroup("bdd1"),   
//      Menu.i("Level 1.5") / "page15" >> Hidden >> LocGroup("bdd1"),      
                     
      
      // more complex because this menu allows anything in the
      // /static path to be visible
//      Menu(Loc("Bootstrap", Link(List("bootstrap-2.2.2"), true, "/bootstrap-2.2.2/index"),
//        "Bootstrap")))

    //def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    //LiftRules.setSiteMapFunc(() => sitemap /*sitemapMutators(sitemap)*/ )
     LiftRules.setSiteMap(Paths.sitemap)
        
    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // What is the function to test if a user is logged in?
    //LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

   
    // Make a transaction span the whole HTTP request
    S.addAround(new LoanWrapper {
      override def apply[T](f: => T): T =
        {
          inTransaction { f }
        }
    })

    
  }
}

object Paths {
  //import xml.NodeSeq
  import scala.xml._
  val index = Menu.i("Home") / "index"
  val cDDLabel = Menu.i("ContentDD") / "contentDD1"
  val cDDDivider1 = Menu("cDDDivider1") / "cDDDivider1" 
  
  val contriesM = code.snippet.Countries.menu
  val aContryM = ACountry.menu
  val aRegionM = ARegion.menu
  
  val l0M = L0.menu
  val l1M = L1.menu
  val l2M = L2.menu
    
  val l111 = Menu.i("Level 1.1.1") / "page111" >> Hidden >> LocGroup("bdd11")
  val l112 = Menu.i("Level 1.1.2") / "page112" >> Hidden >> LocGroup("bdd11")
  val l113 = Menu.i("Level 1.1.3") / "page113" >> Hidden >> LocGroup("bdd11")
  val l114 = Menu.i("Level 1.1.4") / "page114" >> Hidden >> LocGroup("bdd11")
  
  val l121 = Menu.i("Level 1.2.1") / "page121" >> Hidden >> LocGroup("bdd12")
  val l122 = Menu.i("Level 1.2.2") / "page122" >> Hidden >> LocGroup("bdd12")
  
  val l13 = Menu.i("Level 1.3") / "page13" >> Hidden >> LocGroup("bdd1")
  val l14 = Menu.i("Level 1.4") / "page14" >> Hidden >> LocGroup("bdd1")
  val l15 = Menu.i("Level 1.5") / "page15" >> Hidden >> LocGroup("bdd1")
  // more complex because this menu allows anything in the /bootstrap* path to be visible
  val bootstrap222Doc = Menu(Loc("Bootstrap", Link(List("bootstrap-2.2.2"), true, "/bootstrap-2.2.2/index"), S.loc("Bootstrap"  , Text("TB Documentation")) ))
  
  val aDDLabel = Menu.i("ADDLabel") / "add"
  val a1 = Menu.i("A1") / "a1" //>> Hidden >> LocGroup("bdd")
  val a2 = Menu.i("A2") / "a2" //>> Hidden >> LocGroup("bdd")
  val a3 = Menu.i("A3") / "a3" //>> Hidden >> LocGroup("bdd")
  
  val vdivider1 = Menu("vdivider1") / "vdivider1" 
  val uDDLabel = Menu.i("UDDLabel") / "udd"
  val uDDItem1 = Menu("Action") / "uddpage1"
  val uDDItem2 = Menu("Another action") / "uddpage2"
  val uDDItem3 = Menu("Something else here") / "uddpage3"
  val uDDDivider1 = Menu("uDDDivider1") / "uDDDivider1" 
  val uDDItem4 = Menu("Separated link") / "uddpage4"
  
  def sitemap = SiteMap(
      index >> LocGroup("topLeft"),
      cDDLabel >> LocGroup("topLeft") >> PlaceHolder submenus (
        contriesM,
        l0M,
        cDDDivider1 >> FoBo.TBLocInfo.Divider,
        bootstrap222Doc),
        
      aContryM,
      aRegionM,
   
      l1M,
      l2M,

      l111,
      l112,
      l113,   
      l114,
      
      l121,
      l122,
    
      l13,
      l14,   
      l15,      
      
      aDDLabel >> LocGroup("topRight") >> PlaceHolder submenus (
        a1,
        a2,
        a3),
        
      vdivider1  >> LocGroup("topRight") >> FoBo.TBLocInfo.DividerVertical,
        
      uDDLabel >> LocGroup("topRight") >> PlaceHolder submenus (
        uDDItem1,
        uDDItem2,
        uDDItem3,
        uDDDivider1 >> FoBo.TBLocInfo.Divider,
        uDDItem4)
      
     )
}
