package com.tao.example;

import com.tao.example.grpc.basic.GrpcGrpc;
import com.tao.example.grpc.basic.GrpcRequest;
import com.tao.example.grpc.basic.GrpcResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculationClient {
  private static final Logger logger = Logger.getLogger(CalculationClient.class.getName());

  private Server server;

  private void start(int port) throws IOException {
    server = ServerBuilder.forPort(port).addService(new BasicCalImpl()).build().start();
    logger.log(Level.WARNING, "服务已经启动,监听端口：" + port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  logger.log(Level.WARNING, "监听到JVM停止,正在关闭GRPC服务....");
                  CalculationClient.this.stop();
                  logger.log(Level.WARNING, "服务已经停止...");
                }));
  }

  public void stop() {
    Optional.of(server).map(s -> s.shutdown()).orElse(null);
  }

  public void blockUnitShudown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    CalculationClient service  = new CalculationClient();
    service.start(8888);
    service.blockUnitShudown();
  }

  static class BasicCalImpl extends GrpcGrpc.GrpcImplBase {

    @Override
    public void calculation(GrpcRequest request, StreamObserver<GrpcResponse> responseObserver) {
      int num1 = Integer.parseInt(request.getNum1());
      int num2 = Integer.parseInt(request.getNum2());

      GrpcResponse response =
          GrpcResponse.newBuilder()
              .setSub(String.valueOf(num1 + num2))
              .setSub(String.valueOf(num1 - num2))
              .setProduct(String.valueOf(num1 * num2))
              .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }
}
