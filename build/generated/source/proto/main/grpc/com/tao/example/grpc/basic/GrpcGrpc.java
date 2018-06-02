package com.tao.example.grpc.basic;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: basic.proto")
public final class GrpcGrpc {

  private GrpcGrpc() {}

  public static final String SERVICE_NAME = "basic.Grpc";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tao.example.grpc.basic.GrpcRequest,
      com.tao.example.grpc.basic.GrpcResponse> METHOD_CALCULATION =
      io.grpc.MethodDescriptor.<com.tao.example.grpc.basic.GrpcRequest, com.tao.example.grpc.basic.GrpcResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "basic.Grpc", "calculation"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.tao.example.grpc.basic.GrpcRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.tao.example.grpc.basic.GrpcResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcStub newStub(io.grpc.Channel channel) {
    return new GrpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcFutureStub(channel);
  }

  /**
   */
  public static abstract class GrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void calculation(com.tao.example.grpc.basic.GrpcRequest request,
        io.grpc.stub.StreamObserver<com.tao.example.grpc.basic.GrpcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALCULATION, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CALCULATION,
            asyncUnaryCall(
              new MethodHandlers<
                com.tao.example.grpc.basic.GrpcRequest,
                com.tao.example.grpc.basic.GrpcResponse>(
                  this, METHODID_CALCULATION)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcStub extends io.grpc.stub.AbstractStub<GrpcStub> {
    private GrpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcStub(channel, callOptions);
    }

    /**
     */
    public void calculation(com.tao.example.grpc.basic.GrpcRequest request,
        io.grpc.stub.StreamObserver<com.tao.example.grpc.basic.GrpcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CALCULATION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcBlockingStub extends io.grpc.stub.AbstractStub<GrpcBlockingStub> {
    private GrpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.tao.example.grpc.basic.GrpcResponse calculation(com.tao.example.grpc.basic.GrpcRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CALCULATION, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcFutureStub extends io.grpc.stub.AbstractStub<GrpcFutureStub> {
    private GrpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tao.example.grpc.basic.GrpcResponse> calculation(
        com.tao.example.grpc.basic.GrpcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CALCULATION, getCallOptions()), request);
    }
  }

  private static final int METHODID_CALCULATION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALCULATION:
          serviceImpl.calculation((com.tao.example.grpc.basic.GrpcRequest) request,
              (io.grpc.stub.StreamObserver<com.tao.example.grpc.basic.GrpcResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class GrpcDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tao.example.grpc.basic.BasicGprc.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcDescriptorSupplier())
              .addMethod(METHOD_CALCULATION)
              .build();
        }
      }
    }
    return result;
  }
}
