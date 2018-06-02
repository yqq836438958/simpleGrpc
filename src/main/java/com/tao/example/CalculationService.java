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

public class CalculationService {
  protected static int SERVER_PORT = 8888;

  private static final Logger logger = Logger.getLogger(CalculationService.class.getName());

  private Server server;

  /**
   * 启动服务
   *
   * @param port
   * @throws IOException
   */
  private void start(int port) throws IOException {
    server = ServerBuilder.forPort(port).addService(new BasicCalImpl()).build().start();
    logger.log(Level.INFO, "服务已经启动,监听端口：" + port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  logger.log(Level.WARNING, "监听到JVM停止,正在关闭GRPC服务....");
                  CalculationService.this.stop();
                  logger.log(Level.WARNING, "服务已经停止...");
                }));
  }

  /** 关闭服务 */
  public void stop() {
    Optional.of(server).map(s -> s.shutdown()).orElse(null);
  }

  /**
   * 循环运行服务,封锁停止
   *
   * @throws InterruptedException
   */
  public void blockUnitShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * 程序的主运行窗口
   *
   * @param args
   * @throws IOException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    CalculationService service = new CalculationService();
    service.start(SERVER_PORT);
    service.blockUnitShutdown();
  }

  /** 实现的服务类 */
  static class BasicCalImpl extends GrpcGrpc.GrpcImplBase {

    @Override
    public void calculation(GrpcRequest request, StreamObserver<GrpcResponse> responseObserver) {
      // 获取数据信息
      int num1 = Integer.parseInt(request.getNum1());
      int num2 = Integer.parseInt(request.getNum2());
      // 计算数据
      GrpcResponse response =
          GrpcResponse.newBuilder()
              .setSum(String.valueOf(num1 + num2))
              .setSub(String.valueOf(num1 - num2))
              .setProduct(String.valueOf(num1 * num2))
              .build();
      // 返回数据，完成此次请求
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }
}
