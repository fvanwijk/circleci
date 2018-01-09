package com.xebia

import org.scalatest._

import akka.http.scaladsl.testkit._
import akka.http.scaladsl.model._

class ExampleRoutesSpecs extends Suite
    with ScalatestRouteTest
    with RouteTest
    with WordSpecLike
    with Matchers
    with ExampleRoutes {

  "The example routes" should {
    "respond to a GET with a 200 OK" in {
      Get("/api") ~> routes ~> check {
        status should equal(StatusCodes.OK)
      }
    }
  }
}
