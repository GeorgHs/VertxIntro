package com.GeorgHs.intro.vertx_starter.eventbus;

import io.netty.handler.logging.LogLevel;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.time.Duration;

public class PublishSubscribeExample {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Publish());
    vertx.deployVerticle(new SubScriber1());
    vertx.deployVerticle(SubScriber2.class.getName(), new DeploymentOptions().setInstances(2));
  }

  static class Publish extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(Duration.ofSeconds(10).toMillis(), id -> vertx.eventBus().publish(Publish.class.getName(), "A message for everyone!"));
    }
  }

  static class SubScriber1 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      vertx.eventBus().<String>consumer(Publish.class.getName(), message -> {
           System.out.println("Received: {}" + message);
      });
    }
  }

  static class SubScriber2 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      vertx.eventBus().<String>consumer(Publish.class.getName(), message -> {
        System.out.println("Received: {}" + message);
      });
    }
  }
}
