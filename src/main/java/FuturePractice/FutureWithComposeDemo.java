package FuturePractice;

import io.vertx.core.Future;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FutureWithComposeDemo {

  public static void main(String[] args) {
    doSomeShit(1)
      .compose(result -> {
          System.out.println("pass in first compose with : " + result);
          System.out.println("-----------");
          return doSomeShit(result + 1);
        },
        error -> {
          System.out.println("Failed in first compose.");
          System.out.println("-----------");
          return Future.failedFuture(error);
        })
      .compose(result -> {
          System.out.println("pass in second compose with : " + result);
          System.out.println("-----------");
          return doSomeShit(result + 1);
        },
        error -> {
          System.out.println("Failed in second compose.");
          System.out.println("-----------");
          return Future.failedFuture(error);
        })
      .onSuccess(
        result -> {
          System.out.println("succeeded end. result: " + result);
        })
      .onFailure(
        error -> {
          System.out.println("failure end.");
        });
  }

  public static Future<Long> doSomeShit(long count) {
    try {
      System.out.println("Do some shit.");
      TimeUnit.MILLISECONDS.sleep(count * 1000);
//      if (Math.random() < 0.7) {
//        return Future.failedFuture("Bad Luck");
//      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Future.succeededFuture(count);
  }
}

