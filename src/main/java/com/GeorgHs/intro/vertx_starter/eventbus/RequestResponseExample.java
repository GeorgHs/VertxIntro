package com.GeorgHs.intro.vertx_starter.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class RequestResponseExample {

  public static void main(String[] args) {
    Vertx verx = Vertx.vertx();
  }

  static class RequestVerticle extends AbstractVerticle {
    public static final String ADDRESS = "my.request.address";
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      EventBus eventBus = vertx.eventBus();
      final String message = "Hello World!";
      System.out.println("Sending: {}" + message);
      eventBus.request(ADDRESS, message, reply -> {
        System.out.println("Response: {}" + reply.result());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(RequestVerticle.ADDRESS, message -> {
        System.out.println("Received Message {}" + message);
        message.reply("Received your message. Thanks!" + new DeliveryOptions());
      });
      super.start(startPromise);
    }
  }
}
