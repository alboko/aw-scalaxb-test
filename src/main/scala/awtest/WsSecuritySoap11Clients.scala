package awtest

import scalaxb._
import xml._
  
trait WsSecuritySoap11Clients extends scalaxb.Soap11Clients { this: HttpClients =>

    override lazy val soapClient: Soap11Client = new WsSecuritySoap11Client {}

    trait WsSecuritySoap11Client extends Soap11Client {
      
      override def requestResponse(body: scala.xml.NodeSeq, headers: scala.xml.NodeSeq, scope: scala.xml.NamespaceBinding,
                                   address: java.net.URI, webMethod: String, action: Option[java.net.URI]) = {
        
        val wsSecurityL = """
        	<wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
                <wsse:UsernameToken>
                    <wsse:Username>""" + WsSecurityCredentials.username + """</wsse:Username>
                    <wsse:Password>""" + WsSecurityCredentials.password + """</wsse:Password>
               </wsse:UsernameToken>
            </wsse:Security>
        """
        val wsSecurity = """<wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" />""" 
        val wsSecurityHeader = XML.loadString(wsSecurityL)
    	super.requestResponse(body, headers.toSeq ++ wsSecurityHeader, scope, address, webMethod, action)
      }
    }
  }

/**
 * to be set from command line (just to avoid submitting credentials to GitHub)
 */
object WsSecurityCredentials {
  
  var username: String = ""
  var password: String = ""
  
  def set(uname: String, passwd: String) {
    username = uname
    password = passwd
  }
}


