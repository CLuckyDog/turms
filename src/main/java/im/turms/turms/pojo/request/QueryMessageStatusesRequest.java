// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/message/query_message_statuses_request.proto

package im.turms.turms.pojo.request;

/**
 * Protobuf type {@code im.turms.proto.QueryMessageStatusesRequest}
 */
public  final class QueryMessageStatusesRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMessageStatusesRequest)
    QueryMessageStatusesRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueryMessageStatusesRequest.newBuilder() to construct.
  private QueryMessageStatusesRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryMessageStatusesRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QueryMessageStatusesRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryMessageStatusesRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            messageId_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return im.turms.turms.pojo.request.QueryMessageStatusesRequestOuterClass.internal_static_im_turms_proto_QueryMessageStatusesRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return im.turms.turms.pojo.request.QueryMessageStatusesRequestOuterClass.internal_static_im_turms_proto_QueryMessageStatusesRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            im.turms.turms.pojo.request.QueryMessageStatusesRequest.class, im.turms.turms.pojo.request.QueryMessageStatusesRequest.Builder.class);
  }

  public static final int MESSAGE_ID_FIELD_NUMBER = 1;
  private long messageId_;
  /**
   * <code>int64 message_id = 1;</code>
   * @return The messageId.
   */
  public long getMessageId() {
    return messageId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (messageId_ != 0L) {
      output.writeInt64(1, messageId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (messageId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, messageId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof im.turms.turms.pojo.request.QueryMessageStatusesRequest)) {
      return super.equals(obj);
    }
    im.turms.turms.pojo.request.QueryMessageStatusesRequest other = (im.turms.turms.pojo.request.QueryMessageStatusesRequest) obj;

    if (getMessageId()
        != other.getMessageId()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MESSAGE_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMessageId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(im.turms.turms.pojo.request.QueryMessageStatusesRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code im.turms.proto.QueryMessageStatusesRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMessageStatusesRequest)
      im.turms.turms.pojo.request.QueryMessageStatusesRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return im.turms.turms.pojo.request.QueryMessageStatusesRequestOuterClass.internal_static_im_turms_proto_QueryMessageStatusesRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return im.turms.turms.pojo.request.QueryMessageStatusesRequestOuterClass.internal_static_im_turms_proto_QueryMessageStatusesRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              im.turms.turms.pojo.request.QueryMessageStatusesRequest.class, im.turms.turms.pojo.request.QueryMessageStatusesRequest.Builder.class);
    }

    // Construct using im.turms.turms.pojo.request.QueryMessageStatusesRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      messageId_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return im.turms.turms.pojo.request.QueryMessageStatusesRequestOuterClass.internal_static_im_turms_proto_QueryMessageStatusesRequest_descriptor;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryMessageStatusesRequest getDefaultInstanceForType() {
      return im.turms.turms.pojo.request.QueryMessageStatusesRequest.getDefaultInstance();
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryMessageStatusesRequest build() {
      im.turms.turms.pojo.request.QueryMessageStatusesRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryMessageStatusesRequest buildPartial() {
      im.turms.turms.pojo.request.QueryMessageStatusesRequest result = new im.turms.turms.pojo.request.QueryMessageStatusesRequest(this);
      result.messageId_ = messageId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof im.turms.turms.pojo.request.QueryMessageStatusesRequest) {
        return mergeFrom((im.turms.turms.pojo.request.QueryMessageStatusesRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(im.turms.turms.pojo.request.QueryMessageStatusesRequest other) {
      if (other == im.turms.turms.pojo.request.QueryMessageStatusesRequest.getDefaultInstance()) return this;
      if (other.getMessageId() != 0L) {
        setMessageId(other.getMessageId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      im.turms.turms.pojo.request.QueryMessageStatusesRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (im.turms.turms.pojo.request.QueryMessageStatusesRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long messageId_ ;
    /**
     * <code>int64 message_id = 1;</code>
     * @return The messageId.
     */
    public long getMessageId() {
      return messageId_;
    }
    /**
     * <code>int64 message_id = 1;</code>
     * @param value The messageId to set.
     * @return This builder for chaining.
     */
    public Builder setMessageId(long value) {
      
      messageId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 message_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMessageId() {
      
      messageId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMessageStatusesRequest)
  }

  // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMessageStatusesRequest)
  private static final im.turms.turms.pojo.request.QueryMessageStatusesRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new im.turms.turms.pojo.request.QueryMessageStatusesRequest();
  }

  public static im.turms.turms.pojo.request.QueryMessageStatusesRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryMessageStatusesRequest>
      PARSER = new com.google.protobuf.AbstractParser<QueryMessageStatusesRequest>() {
    @java.lang.Override
    public QueryMessageStatusesRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QueryMessageStatusesRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryMessageStatusesRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryMessageStatusesRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public im.turms.turms.pojo.request.QueryMessageStatusesRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

