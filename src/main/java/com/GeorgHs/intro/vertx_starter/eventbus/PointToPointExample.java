package com.GeorgHs.intro.vertx_starter.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class PointToPointExample {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Sender());
    vertx.deployVerticle(new Receiver());

  }

  static class Sender extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(1000, id -> {
        // Send a message every second
        vertx.eventBus().send(Sender.class.getName(),"sender.address");
      });
      super.start(startPromise);
    }
  }

  static class Receiver extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {

      });
      super.start(startPromise);
    }
  }
}
