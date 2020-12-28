package com.GeorgHs.intro.vertx_starter.eventloop;

import io.vertx.core.*;
import io.vertx.core.impl.logging.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class EventLoopExample extends AbstractVerticle {

  //private static final Logger LOG = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(
      new VertxOptions()
      .setMaxEventLoopExecuteTime(500)
      .setMaxWorkerExecuteTimeUnit(TimeUnit.MILLISECONDS)
      .setBlockedThreadCheckInterval(1)
      .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
      .setEventLoopPoolSize(2)
    );
    vertx.deployVerticle(EventLoopExample.class.getName(),
    new DeploymentOptions().setInstances(4)
    );
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //Log.debug("Start {}", getClass().getName());
    startPromise.complete();
    Thread.sleep(5000);
  }
}
