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
    FoBo.InitParam.ToolKit=FoBo.Bootstrap210
    FoBo.InitParam.ToolKit=FoBo.PrettifyJun2011
    FoBo.init()
    //Just to show you do not need to use the FoBo jquery
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery172
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
    def sitemap = SiteMap(
      Menu.i("Home") / "index",
      code.snippet.Countries.menu,
      ACountry.menu,
      ARegion.menu,
      L0.menu,
      L1.menu,
      L2.menu,
      Menu.i("A1") / "a1" >> Hidden >> LocGroup("bdd"),
      Menu.i("A2") / "a2" >> Hidden >> LocGroup("bdd"),
      Menu.i("A3") / "a3" >> Hidden >> LocGroup("bdd"),

      Menu.i("Level 1.1.1") / "page111" >> Hidden >> LocGroup("bdd11"),
      Menu.i("Level 1.1.2") / "page112" >> Hidden >> LocGroup("bdd11"),
      Menu.i("Level 1.1.3") / "page113" >> Hidden >> LocGroup("bdd11"),   
      Menu.i("Level 1.1.4") / "page114" >> Hidden >> LocGroup("bdd11"),
      
      Menu.i("Level 1.2.1") / "page121" >> Hidden >> LocGroup("bdd12"),
      Menu.i("Level 1.2.2") / "page122" >> Hidden >> LocGroup("bdd12"),
    
      Menu.i("Level 1.3") / "page13" >> Hidden >> LocGroup("bdd1"),
      Menu.i("Level 1.4") / "page14" >> Hidden >> LocGroup("bdd1"),   
      Menu.i("Level 1.5") / "page15" >> Hidden >> LocGroup("bdd1"),      
                     
      
      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Bootstrap", Link(List("bootstrap-2.1.0"), true, "/bootstrap-2.1.0/index"),
        "Bootstrap")))

    //def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap /*sitemapMutators(sitemap)*/ )

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
