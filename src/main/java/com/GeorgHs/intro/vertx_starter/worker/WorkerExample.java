package com.GeorgHs.intro.vertx_starter.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class WorkerExample extends AbstractVerticle {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
    vertx.executeBlocking(event -> {
    System.out.println("Executing blocking codes");
      try {
        Thread.sleep(5000);
        event.fail("Force Failed");
      } catch (InterruptedException e) {
        System.out.println("Failed: " + e);
        event.fail(e);
      }
    }, result -> {
      if (result.succeeded()) {
        System.out.println("Blocking call done.");
      } else {
        System.out.println("Blocking call failed due to:" + result.cause());
      }
    });


    super.start(startPromise);
  }
}
