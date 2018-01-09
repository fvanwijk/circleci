package com.xebia

import scala.concurrent.duration._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.util._

trait ExampleRoutes {
  implicit val timeout: Timeout = 2.seconds

  def routes = {
    pathPrefix("api") {
      pathEnd {
        get {
          complete(StatusCodes.OK)
        }
      }
    }
  }
}
