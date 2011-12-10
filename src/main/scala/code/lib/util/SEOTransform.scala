package code.lib.util

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.Normalizer.Form;

object SEOTransform {

  /**
   * Input string will be converted to a "search engine optimization friendly string" 
   * using canonical decomposition, lower case, replacing space with "-" 
   * for example 
   * "Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ" ==> "this-is-a-funky-string"
   */
  def toSEOFriendly(in:String):String = {
    var s = normalize(in).replace(" ", "-").replace("'", "").replace("(", "").replace(")", "").replace("[", "").replace("]", "")
    URLEncoder.encode(s, "UTF-8");
  }
  /*
   * Using the normalization Form D (NFD) i.e Canonical Decomposition 
   * Getting rid of accents and converting them to regular letters for example
   * "Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ" ==> "This is a funky String"
   * see http://download.oracle.com/javase/6/docs/api/java/text/Normalizer.html
   */
  private def normalize(in:String):String = {
    Normalizer.normalize(in, Form.NFD).replaceAll("[^\\p{ASCII}]","").toLowerCase()
  }
  
}