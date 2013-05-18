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

import net.liftmodules.{FoBo}

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    
    // where to search snippet
    LiftRules.addToPackages("code")
    
    //The FoBo setup and init
    FoBo.InitParam.JQuery=FoBo.JQuery182  
    FoBo.InitParam.ToolKit=FoBo.Bootstrap231
    FoBo.InitParam.ToolKit=FoBo.FontAwesome300
    FoBo.InitParam.ToolKit=FoBo.PrettifyJun2011
    FoBo.init()

 
    /*un-comment and switch to db of your liking */
    MySchemaHelper.initSquerylRecordWithInMemoryDB
    //MySchemaHelper.initSquerylRecordWithMySqlDB
    //MySchemaHelper.initSquerylRecordWithPostgresDB

    Props.mode match {
      case Props.RunModes.Development => {
        logger.info("RunMode is DEVELOPMENT")
        /*OBS! It may not be safe to use schemify in a production env*/
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
  
  /*
   * This SiteMap uses FoBo specific LocInfo objects to build TB menues with dividers 
   * (vertical/horizontal), headers (labels). You will find more information at [2]
   * 
   * If you are looking for examples on how to enable use of MetaMegaProtoUser AddUserMenusXXXX 
   * methods (for example User.AddUserMenusAfter with authentication) Take a look at [1] and [2]
   *  
   * [1] https://github.com/karma4u101/FoBo/issues/22#issuecomment-11932972 or 
   * [2] http://www.media4u101.se/fobo-lift-template-demo/libo#fobo-snippets   
   */
  
  //menu dividers
  val divider1   = Menu("divider1") / "divider1" 
  val divider2   = Menu("divider2") / "divider2"
  val divider3   = Menu("divider3") / "divider3"
  val divider4   = Menu("divider4") / "divider4"
  val vdivider1  = Menu("vdivider1") / "vdivider1"   
  
  //drop down labels
  val ddLabel1   = Menu.i("DDLabel1") / "ddlabel1"
  val ddLabel2   = Menu.i("DDLabel2") / "ddlabel2"
  val ddLabel3   = Menu.i("DDLabel3") / "ddlabel3"
  
  //nav headers
  val navHeader1 = Menu.i("NavHeader1") / "navHeader1"
  
  val index      = Menu.i("Home") / "index"
  
  val contriesM  = code.snippet.Countries.menu
  val aContryM   = ACountry.menu
  val aRegionM   = ARegion.menu
  
  val l0M        = L0.menu
  val l1M        = L1.menu
  val l2M        = L2.menu
    
  val l111       = Menu.i("Level 1.1.1") / "page111" 
  val l112       = Menu.i("Level 1.1.2") / "page112" 
  val l113       = Menu.i("Level 1.1.3") / "page113" 
  val l114       = Menu.i("Level 1.1.4") / "page114" 
  
  val l121       = Menu.i("Level 1.2.1") / "page121" 
  val l122       = Menu.i("Level 1.2.2") / "page122" 
  
  val l13        = Menu.i("Level 1.3") / "page13" 
  val l14        = Menu.i("Level 1.4") / "page14"
  val l15        = Menu.i("Level 1.5") / "page15" 
  
  // more complex because this menu allows anything in the /bootstrap* path to be visible
  val tb222Doc   = Menu(Loc("Bootstrap", Link(List("bootstrap-2.2.2"), true, "/bootstrap-2.2.2/index"), S.loc("Bootstrap"  , Text("TB Documentation")),LocGroup("nl1") ))
  
  val a1         = Menu.i("A1") / "a1" //>> Hidden >> LocGroup("bdd")
  val a2         = Menu.i("A2") / "a2" //>> Hidden >> LocGroup("bdd")
  val a3         = Menu.i("A3") / "a3" //>> Hidden >> LocGroup("bdd")
  
  val uDDItem1   = Menu.i("Action") / "uddpage1"
  val uDDItem2   = Menu.i("Another action") / "uddpage2"
  val uDDItem3   = Menu.i("Something else here") / "uddpage3"
  val uDDItem4   = Menu.i("Separated link") / "uddpage4"
  
  val nlHelp     = Menu.i("NLHelp") / "helpindex"
  
  def sitemap = SiteMap(
      navHeader1    >> LocGroup("nl1") >> FoBo.TBLocInfo.NavHeader,
      index         >> LocGroup("topLeft","nl1"),
      ddLabel1      >> LocGroup("topLeft") >> PlaceHolder submenus (
        contriesM   >> LocGroup("nl1"),
        l0M         >> LocGroup("nl1"),
        divider1    >> FoBo.TBLocInfo.Divider,
        tb222Doc ), 
      aContryM,
      aRegionM,
      l1M,
      l2M,
      
      ddLabel3    >> LocGroup("topRight") >> PlaceHolder submenus (
        a1,
        a2,
        a3),
      vdivider1   >> LocGroup("topRight") >> FoBo.TBLocInfo.DividerVertical,
        
      ddLabel2    >> LocGroup("topRight") >> PlaceHolder submenus (
        uDDItem1,
        uDDItem2,
        uDDItem3,
        divider3  >> FoBo.TBLocInfo.Divider,
        uDDItem4),
        
      divider4    >> LocGroup("nl1") >> FoBo.TBLocInfo.Divider,  
      nlHelp      >> LocGroup("nl1"),
      
      l111 >> Hidden >> LocGroup("bdd11"),
      l112 >> Hidden >> LocGroup("bdd11"),
      l113 >> Hidden >> LocGroup("bdd11"),   
      l114 >> Hidden >> LocGroup("bdd11"),
      
      l121 >> Hidden >> LocGroup("bdd12"),
      l122 >> Hidden >> LocGroup("bdd12"),
    
      l13 >> Hidden >> LocGroup("bdd1"),
      l14 >> Hidden >> LocGroup("bdd1"),   
      l15 >> Hidden >> LocGroup("bdd1")           
     )
}
