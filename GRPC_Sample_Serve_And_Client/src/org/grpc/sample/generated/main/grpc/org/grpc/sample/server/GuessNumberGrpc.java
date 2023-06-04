package org.grpc.sample.server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Guess service definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.1)",
    comments = "Source: guessnumber.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GuessNumberGrpc {

  private GuessNumberGrpc() {}

  public static final String SERVICE_NAME = "GuessNumber";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.grpc.sample.server.GuessRequest,
      org.grpc.sample.server.HowLuckyResponse> getLetUsSeeHowLuckyYouMayBeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "letUsSeeHowLuckyYouMayBe",
      requestType = org.grpc.sample.server.GuessRequest.class,
      responseType = org.grpc.sample.server.HowLuckyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.grpc.sample.server.GuessRequest,
      org.grpc.sample.server.HowLuckyResponse> getLetUsSeeHowLuckyYouMayBeMethod() {
    io.grpc.MethodDescriptor<org.grpc.sample.server.GuessRequest, org.grpc.sample.server.HowLuckyResponse> getLetUsSeeHowLuckyYouMayBeMethod;
    if ((getLetUsSeeHowLuckyYouMayBeMethod = GuessNumberGrpc.getLetUsSeeHowLuckyYouMayBeMethod) == null) {
      synchronized (GuessNumberGrpc.class) {
        if ((getLetUsSeeHowLuckyYouMayBeMethod = GuessNumberGrpc.getLetUsSeeHowLuckyYouMayBeMethod) == null) {
          GuessNumberGrpc.getLetUsSeeHowLuckyYouMayBeMethod = getLetUsSeeHowLuckyYouMayBeMethod =
              io.grpc.MethodDescriptor.<org.grpc.sample.server.GuessRequest, org.grpc.sample.server.HowLuckyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "letUsSeeHowLuckyYouMayBe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.sample.server.GuessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.sample.server.HowLuckyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuessNumberMethodDescriptorSupplier("letUsSeeHowLuckyYouMayBe"))
              .build();
        }
      }
    }
    return getLetUsSeeHowLuckyYouMayBeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GuessNumberStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuessNumberStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuessNumberStub>() {
        @java.lang.Override
        public GuessNumberStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuessNumberStub(channel, callOptions);
        }
      };
    return GuessNumberStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GuessNumberBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuessNumberBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuessNumberBlockingStub>() {
        @java.lang.Override
        public GuessNumberBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuessNumberBlockingStub(channel, callOptions);
        }
      };
    return GuessNumberBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GuessNumberFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuessNumberFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuessNumberFutureStub>() {
        @java.lang.Override
        public GuessNumberFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuessNumberFutureStub(channel, callOptions);
        }
      };
    return GuessNumberFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Guess service definition
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void letUsSeeHowLuckyYouMayBe(org.grpc.sample.server.GuessRequest request,
        io.grpc.stub.StreamObserver<org.grpc.sample.server.HowLuckyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLetUsSeeHowLuckyYouMayBeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GuessNumber.
   * <pre>
   * Guess service definition
   * </pre>
   */
  public static abstract class GuessNumberImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GuessNumberGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GuessNumber.
   * <pre>
   * Guess service definition
   * </pre>
   */
  public static final class GuessNumberStub
      extends io.grpc.stub.AbstractAsyncStub<GuessNumberStub> {
    private GuessNumberStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuessNumberStub(channel, callOptions);
    }

    /**
     */
    public void letUsSeeHowLuckyYouMayBe(org.grpc.sample.server.GuessRequest request,
        io.grpc.stub.StreamObserver<org.grpc.sample.server.HowLuckyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLetUsSeeHowLuckyYouMayBeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GuessNumber.
   * <pre>
   * Guess service definition
   * </pre>
   */
  public static final class GuessNumberBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GuessNumberBlockingStub> {
    private GuessNumberBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuessNumberBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.grpc.sample.server.HowLuckyResponse letUsSeeHowLuckyYouMayBe(org.grpc.sample.server.GuessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLetUsSeeHowLuckyYouMayBeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GuessNumber.
   * <pre>
   * Guess service definition
   * </pre>
   */
  public static final class GuessNumberFutureStub
      extends io.grpc.stub.AbstractFutureStub<GuessNumberFutureStub> {
    private GuessNumberFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuessNumberFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.grpc.sample.server.HowLuckyResponse> letUsSeeHowLuckyYouMayBe(
        org.grpc.sample.server.GuessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLetUsSeeHowLuckyYouMayBeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LET_US_SEE_HOW_LUCKY_YOU_MAY_BE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LET_US_SEE_HOW_LUCKY_YOU_MAY_BE:
          serviceImpl.letUsSeeHowLuckyYouMayBe((org.grpc.sample.server.GuessRequest) request,
              (io.grpc.stub.StreamObserver<org.grpc.sample.server.HowLuckyResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getLetUsSeeHowLuckyYouMayBeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.grpc.sample.server.GuessRequest,
              org.grpc.sample.server.HowLuckyResponse>(
                service, METHODID_LET_US_SEE_HOW_LUCKY_YOU_MAY_BE)))
        .build();
  }

  private static abstract class GuessNumberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GuessNumberBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.grpc.sample.server.Guessnumber.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GuessNumber");
    }
  }

  private static final class GuessNumberFileDescriptorSupplier
      extends GuessNumberBaseDescriptorSupplier {
    GuessNumberFileDescriptorSupplier() {}
  }

  private static final class GuessNumberMethodDescriptorSupplier
      extends GuessNumberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GuessNumberMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GuessNumberGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GuessNumberFileDescriptorSupplier())
              .addMethod(getLetUsSeeHowLuckyYouMayBeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
