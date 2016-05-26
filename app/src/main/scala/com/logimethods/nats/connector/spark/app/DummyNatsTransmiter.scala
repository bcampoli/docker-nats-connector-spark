/*******************************************************************************
 * Copyright (c) 2016 Logimethods
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *******************************************************************************/

package com.logimethods.nats.connector.spark.app

import java.util.Properties
import org.nats._

// @see https://github.com/tyagihas/scala_nats
object DummyNatsTransmiter extends App {
  Thread.sleep(2000)
  
  val properties = new Properties()
//  properties.setProperty(/*io.nats.client.Constants.PROP_URL*/ "io.nats.client.url", "nats://nats-main:4222")
  //@see https://github.com/tyagihas/java_nats/blob/master/src/main/java/org/nats/Connection.java
//  properties.put("uri", "nats://nats-main:4222")
  properties.put("servers", "nats://nats-main:4222")
//  properties.put("verbose", java.lang.Boolean.TRUE)
  val conn = Conn.connect(properties)
  
  println("Will transmit messages from " + inputSubject + " to " + outputSubject)

  val inputSubject = args(0)
  val outputSubject = args(1)
  conn.subscribe(inputSubject, (msg:Msg) => {
    println("Transmiting message from " + inputSubject + " to " + outputSubject + ": " + msg.body)
    conn.publish(outputSubject, msg.body)
    })
}