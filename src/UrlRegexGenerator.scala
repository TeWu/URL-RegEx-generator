
import UrlRegexGenerator.Helpers._


object UrlRegexGenerator {
  val regex = URL_regex.regex

  object Helpers {
    val digit = """\d"""
    val letter = """\p{L}"""
    val character = """@%+.)(_-"""

    val dl = "[" + digit + letter + "]"
    val dlc = "[" + digit + letter + character + "]"
  }

  object URL_regex {
    val scheme = """(https?:\/\/)"""
    val domain = {
      val port = s"(:$digit{1,5})"
      val domainName = s"($dlc+)\\.($dlc{2,6})"
      val ipAddr = s"($digit{1,3}\\.$digit{1,3}\\.$digit{1,3}\\.$digit{1,3}$port?)"
      val hostName = s"($dl+$port?)"
      s"($domainName|$ipAddr|$hostName)"
    }
    val path = s"(\\/$dlc+)*"
    val queryString = s"(\\?$dlc+=$dlc+(&$dlc+=$dlc+)*)?"
    val fragmentId = s"(#$dlc*)?"

    val regex = (scheme + domain + path + queryString + fragmentId).r
  }



  def main(args: Array[String]) {
    val url = "http://localhost:22/ontB/sparql?test=tak&other=347#fraGMet2"

    println(regex.pattern.toString)
    println(regex.pattern.toString.length)
    println(regex.pattern.matcher(url).matches())


    val matcher = regex.pattern.matcher(url)
    matcher.matches()

    println()
    val groupCount = matcher.groupCount()
    for (i <- 0 to groupCount)
      println(i + ">" + matcher.group(i))

  }
}
