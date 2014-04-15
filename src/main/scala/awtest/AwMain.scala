package awtest

import aw._
import scalaxb._

object AwMain {

  /**
   * very simple test
   * 
   * see: http://scalaxb.org/wsdl-support for more details
   */
  def main(args: Array[String]): Unit = {
    
    WsSecurityCredentials.set(args(0), args(1))
    
    val service = (new aw.ServicesSOAPBindings with WsSecuritySoap11Clients with scalaxb.DispatchHttpClients {}).service
    
    val soapRespond = service.listProviders(3) 
    
    soapRespond match {
      case Right(respond) => 
        println(s"Respond: code ${respond.ErrorCode}, messsage: ${respond.ErrorMessage}, Providers: ${respond.Providers.size}")
        if (!respond.Providers.isEmpty)
          listProviders(respond.Providers)
      case Left(failure) => println("Failure: " + failure)
    }    
  }
  
  def listProviders(providers: Seq[aw.ProviderInfoType]) {
    providers filter ( p => (p.Kind == 5 || p.Kind == 6) ) foreach { p =>
      println(s"${p.Code} [${p.DisplayName}]")
    }
    
    
    /** Provider info
     *  
case class ProviderInfoType(Kind: BigInt,
  Code: String,
  DisplayName: String,
  ProviderName: String,
  ProgramName: String,
  Login: aw.InputType,
  Login2: Option[aw.InputType] = None,
  Login3: Option[aw.InputType] = None,
  Password: aw.InputType,
  Properties: Seq[aw.PropertyInfoType] = Nil,
  AutoLogin: Boolean,
  DeepLinking: Boolean,
  CanCheckConfirmation: Boolean,
  CanCheckItinerary: Boolean,
  CanCheckExpiration: Int,
  ConfirmationNumberFields: Seq[aw.InputType] = Nil,
  HistoryColumns: Seq[aw.PropertyInfoType] = Nil,
  EliteLevelsCount: Int,
  CanParseHistory: Boolean)
  * 
  */
    
  }

}