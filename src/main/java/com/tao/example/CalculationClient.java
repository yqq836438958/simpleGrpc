package com.tao.example;

import com.tao.example.grpc.basic.GrpcGrpc;
import com.tao.example.grpc.basic.GrpcRequest;
import com.tao.example.grpc.basic.GrpcResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static com.tao.example.CalculationService.SERVER_PORT;

public class CalculationClient {
  private static final Logger logger = Logger.getLogger(CalculationClient.class.getName());

  private ManagedChannel managedChannel;

  private GrpcGrpc.GrpcBlockingStub blockingStub;

  public CalculationClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
  }

  public void sendMessage(String num1, String num2) {
    logger.log(Level.INFO, "尝试发送: num1 = " + num1 + ",num2 = " + num2);
    GrpcRequest request = GrpcRequest.newBuilder().setNum1(num1).setNum2(num2).build();
    GrpcResponse response = null;
    try {
      response = blockingStub.calculation(request);
      System.out.println("两数的和 = " + response.getSum());
      System.out.println("两数的差 = " + response.getSub());
      System.out.println("两数的积 = " + response.getProduct());
    } catch (StatusRuntimeException ex) {
      logger.log(Level.WARNING, "发送消息出现异常", ex);
    }
  }

  /**
   * 关闭客户端
   *
   * @throws InterruptedException
   */
  public void shutdown() throws InterruptedException {
    managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  CalculationClient(ManagedChannelBuilder<?> channelBuilder) {
    managedChannel = channelBuilder.build();
    blockingStub = GrpcGrpc.newBlockingStub(managedChannel);
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    String host = "127.0.0.1";
    CalculationClient client = new CalculationClient(host, SERVER_PORT);
    Scanner scanner = new Scanner(System.in);
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    System.out.print("请输入Num1:");
    String num1Str = scanner.next();
    if (!pattern.matcher(num1Str).matches()) {
      logger.log(Level.WARNING, "num1不是一个整数,程序无法运行");
    }
    System.out.print("请输入Num2:");
    String num2Str = scanner.next();
    if (!pattern.matcher(num2Str).matches()) {
      logger.log(Level.WARNING, "num2不是一个整数,程序无法运行");
    }
    client.sendMessage(num1Str, num2Str);
  }
}
