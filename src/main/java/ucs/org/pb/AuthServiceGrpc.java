package ucs.org.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: auth.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final String SERVICE_NAME = "pb.AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<UcsPb.AuthenticationRequest,
      UcsPb.Result> getAuthenticationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Authentication",
      requestType = UcsPb.AuthenticationRequest.class,
      responseType = UcsPb.Result.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UcsPb.AuthenticationRequest,
      UcsPb.Result> getAuthenticationMethod() {
    io.grpc.MethodDescriptor<UcsPb.AuthenticationRequest, UcsPb.Result> getAuthenticationMethod;
    if ((getAuthenticationMethod = AuthServiceGrpc.getAuthenticationMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getAuthenticationMethod = AuthServiceGrpc.getAuthenticationMethod) == null) {
          AuthServiceGrpc.getAuthenticationMethod = getAuthenticationMethod =
              io.grpc.MethodDescriptor.<UcsPb.AuthenticationRequest, UcsPb.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Authentication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.AuthenticationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.Result.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Authentication"))
              .build();
        }
      }
    }
    return getAuthenticationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UcsPb.AuthorizationRequest,
      UcsPb.Result> getAuthorizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Authorization",
      requestType = UcsPb.AuthorizationRequest.class,
      responseType = UcsPb.Result.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UcsPb.AuthorizationRequest,
      UcsPb.Result> getAuthorizationMethod() {
    io.grpc.MethodDescriptor<UcsPb.AuthorizationRequest, UcsPb.Result> getAuthorizationMethod;
    if ((getAuthorizationMethod = AuthServiceGrpc.getAuthorizationMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getAuthorizationMethod = AuthServiceGrpc.getAuthorizationMethod) == null) {
          AuthServiceGrpc.getAuthorizationMethod = getAuthorizationMethod =
              io.grpc.MethodDescriptor.<UcsPb.AuthorizationRequest, UcsPb.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Authorization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.AuthorizationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.Result.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Authorization"))
              .build();
        }
      }
    }
    return getAuthorizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UcsPb.RenewTokenRequest,
      UcsPb.RenewTokenResult> getRenewTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RenewToken",
      requestType = UcsPb.RenewTokenRequest.class,
      responseType = UcsPb.RenewTokenResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UcsPb.RenewTokenRequest,
      UcsPb.RenewTokenResult> getRenewTokenMethod() {
    io.grpc.MethodDescriptor<UcsPb.RenewTokenRequest, UcsPb.RenewTokenResult> getRenewTokenMethod;
    if ((getRenewTokenMethod = AuthServiceGrpc.getRenewTokenMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getRenewTokenMethod = AuthServiceGrpc.getRenewTokenMethod) == null) {
          AuthServiceGrpc.getRenewTokenMethod = getRenewTokenMethod =
              io.grpc.MethodDescriptor.<UcsPb.RenewTokenRequest, UcsPb.RenewTokenResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RenewToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.RenewTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UcsPb.RenewTokenResult.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("RenewToken"))
              .build();
        }
      }
    }
    return getRenewTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AuthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void authentication(UcsPb.AuthenticationRequest request,
                               io.grpc.stub.StreamObserver<UcsPb.Result> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthenticationMethod(), responseObserver);
    }

    /**
     */
    public void authorization(UcsPb.AuthorizationRequest request,
                              io.grpc.stub.StreamObserver<UcsPb.Result> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthorizationMethod(), responseObserver);
    }

    /**
     */
    public void renewToken(UcsPb.RenewTokenRequest request,
                           io.grpc.stub.StreamObserver<UcsPb.RenewTokenResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRenewTokenMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthenticationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                UcsPb.AuthenticationRequest,
                UcsPb.Result>(
                  this, METHODID_AUTHENTICATION)))
          .addMethod(
            getAuthorizationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                UcsPb.AuthorizationRequest,
                UcsPb.Result>(
                  this, METHODID_AUTHORIZATION)))
          .addMethod(
            getRenewTokenMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                UcsPb.RenewTokenRequest,
                UcsPb.RenewTokenResult>(
                  this, METHODID_RENEW_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class AuthServiceStub extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void authentication(UcsPb.AuthenticationRequest request,
                               io.grpc.stub.StreamObserver<UcsPb.Result> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthenticationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void authorization(UcsPb.AuthorizationRequest request,
                              io.grpc.stub.StreamObserver<UcsPb.Result> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthorizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void renewToken(UcsPb.RenewTokenRequest request,
                           io.grpc.stub.StreamObserver<UcsPb.RenewTokenResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRenewTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public UcsPb.Result authentication(UcsPb.AuthenticationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthenticationMethod(), getCallOptions(), request);
    }

    /**
     */
    public UcsPb.Result authorization(UcsPb.AuthorizationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthorizationMethod(), getCallOptions(), request);
    }

    /**
     */
    public UcsPb.RenewTokenResult renewToken(UcsPb.RenewTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRenewTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UcsPb.Result> authentication(
        UcsPb.AuthenticationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthenticationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UcsPb.Result> authorization(
        UcsPb.AuthorizationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthorizationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UcsPb.RenewTokenResult> renewToken(
        UcsPb.RenewTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRenewTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHENTICATION = 0;
  private static final int METHODID_AUTHORIZATION = 1;
  private static final int METHODID_RENEW_TOKEN = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATION:
          serviceImpl.authentication((UcsPb.AuthenticationRequest) request,
              (io.grpc.stub.StreamObserver<UcsPb.Result>) responseObserver);
          break;
        case METHODID_AUTHORIZATION:
          serviceImpl.authorization((UcsPb.AuthorizationRequest) request,
              (io.grpc.stub.StreamObserver<UcsPb.Result>) responseObserver);
          break;
        case METHODID_RENEW_TOKEN:
          serviceImpl.renewToken((UcsPb.RenewTokenRequest) request,
              (io.grpc.stub.StreamObserver<UcsPb.RenewTokenResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return UcsPb.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getAuthenticationMethod())
              .addMethod(getAuthorizationMethod())
              .addMethod(getRenewTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
