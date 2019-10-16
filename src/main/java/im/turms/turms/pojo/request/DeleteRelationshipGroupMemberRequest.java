// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/relationship/delete_relationship_group_member_request.proto

package im.turms.turms.pojo.request;

/**
 * Protobuf type {@code im.turms.proto.DeleteRelationshipGroupMemberRequest}
 */
public  final class DeleteRelationshipGroupMemberRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteRelationshipGroupMemberRequest)
    DeleteRelationshipGroupMemberRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DeleteRelationshipGroupMemberRequest.newBuilder() to construct.
  private DeleteRelationshipGroupMemberRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DeleteRelationshipGroupMemberRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DeleteRelationshipGroupMemberRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DeleteRelationshipGroupMemberRequest(
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

            userId_ = input.readInt64();
            break;
          }
          case 16: {

            groupIndex_ = input.readInt32();
            break;
          }
          case 26: {
            com.google.protobuf.Int32Value.Builder subBuilder = null;
            if (targetGroupIndex_ != null) {
              subBuilder = targetGroupIndex_.toBuilder();
            }
            targetGroupIndex_ = input.readMessage(com.google.protobuf.Int32Value.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(targetGroupIndex_);
              targetGroupIndex_ = subBuilder.buildPartial();
            }

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
    return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.class, im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.Builder.class);
  }

  public static final int USER_ID_FIELD_NUMBER = 1;
  private long userId_;
  /**
   * <code>int64 user_id = 1;</code>
   * @return The userId.
   */
  public long getUserId() {
    return userId_;
  }

  public static final int GROUP_INDEX_FIELD_NUMBER = 2;
  private int groupIndex_;
  /**
   * <code>int32 group_index = 2;</code>
   * @return The groupIndex.
   */
  public int getGroupIndex() {
    return groupIndex_;
  }

  public static final int TARGET_GROUP_INDEX_FIELD_NUMBER = 3;
  private com.google.protobuf.Int32Value targetGroupIndex_;
  /**
   * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
   * @return Whether the targetGroupIndex field is set.
   */
  public boolean hasTargetGroupIndex() {
    return targetGroupIndex_ != null;
  }
  /**
   * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
   * @return The targetGroupIndex.
   */
  public com.google.protobuf.Int32Value getTargetGroupIndex() {
    return targetGroupIndex_ == null ? com.google.protobuf.Int32Value.getDefaultInstance() : targetGroupIndex_;
  }
  /**
   * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
   */
  public com.google.protobuf.Int32ValueOrBuilder getTargetGroupIndexOrBuilder() {
    return getTargetGroupIndex();
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
    if (userId_ != 0L) {
      output.writeInt64(1, userId_);
    }
    if (groupIndex_ != 0) {
      output.writeInt32(2, groupIndex_);
    }
    if (targetGroupIndex_ != null) {
      output.writeMessage(3, getTargetGroupIndex());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (userId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, userId_);
    }
    if (groupIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, groupIndex_);
    }
    if (targetGroupIndex_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getTargetGroupIndex());
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
    if (!(obj instanceof im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest)) {
      return super.equals(obj);
    }
    im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest other = (im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest) obj;

    if (getUserId()
        != other.getUserId()) return false;
    if (getGroupIndex()
        != other.getGroupIndex()) return false;
    if (hasTargetGroupIndex() != other.hasTargetGroupIndex()) return false;
    if (hasTargetGroupIndex()) {
      if (!getTargetGroupIndex()
          .equals(other.getTargetGroupIndex())) return false;
    }
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
    hash = (37 * hash) + USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUserId());
    hash = (37 * hash) + GROUP_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getGroupIndex();
    if (hasTargetGroupIndex()) {
      hash = (37 * hash) + TARGET_GROUP_INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getTargetGroupIndex().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parseFrom(
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
  public static Builder newBuilder(im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest prototype) {
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
   * Protobuf type {@code im.turms.proto.DeleteRelationshipGroupMemberRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteRelationshipGroupMemberRequest)
      im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.class, im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.Builder.class);
    }

    // Construct using im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.newBuilder()
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
      userId_ = 0L;

      groupIndex_ = 0;

      if (targetGroupIndexBuilder_ == null) {
        targetGroupIndex_ = null;
      } else {
        targetGroupIndex_ = null;
        targetGroupIndexBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest getDefaultInstanceForType() {
      return im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.getDefaultInstance();
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest build() {
      im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest buildPartial() {
      im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest result = new im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest(this);
      result.userId_ = userId_;
      result.groupIndex_ = groupIndex_;
      if (targetGroupIndexBuilder_ == null) {
        result.targetGroupIndex_ = targetGroupIndex_;
      } else {
        result.targetGroupIndex_ = targetGroupIndexBuilder_.build();
      }
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
      if (other instanceof im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest) {
        return mergeFrom((im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest other) {
      if (other == im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest.getDefaultInstance()) return this;
      if (other.getUserId() != 0L) {
        setUserId(other.getUserId());
      }
      if (other.getGroupIndex() != 0) {
        setGroupIndex(other.getGroupIndex());
      }
      if (other.hasTargetGroupIndex()) {
        mergeTargetGroupIndex(other.getTargetGroupIndex());
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
      im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long userId_ ;
    /**
     * <code>int64 user_id = 1;</code>
     * @return The userId.
     */
    public long getUserId() {
      return userId_;
    }
    /**
     * <code>int64 user_id = 1;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(long value) {
      
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 user_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      
      userId_ = 0L;
      onChanged();
      return this;
    }

    private int groupIndex_ ;
    /**
     * <code>int32 group_index = 2;</code>
     * @return The groupIndex.
     */
    public int getGroupIndex() {
      return groupIndex_;
    }
    /**
     * <code>int32 group_index = 2;</code>
     * @param value The groupIndex to set.
     * @return This builder for chaining.
     */
    public Builder setGroupIndex(int value) {
      
      groupIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 group_index = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearGroupIndex() {
      
      groupIndex_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Int32Value targetGroupIndex_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Int32Value, com.google.protobuf.Int32Value.Builder, com.google.protobuf.Int32ValueOrBuilder> targetGroupIndexBuilder_;
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     * @return Whether the targetGroupIndex field is set.
     */
    public boolean hasTargetGroupIndex() {
      return targetGroupIndexBuilder_ != null || targetGroupIndex_ != null;
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     * @return The targetGroupIndex.
     */
    public com.google.protobuf.Int32Value getTargetGroupIndex() {
      if (targetGroupIndexBuilder_ == null) {
        return targetGroupIndex_ == null ? com.google.protobuf.Int32Value.getDefaultInstance() : targetGroupIndex_;
      } else {
        return targetGroupIndexBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public Builder setTargetGroupIndex(com.google.protobuf.Int32Value value) {
      if (targetGroupIndexBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        targetGroupIndex_ = value;
        onChanged();
      } else {
        targetGroupIndexBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public Builder setTargetGroupIndex(
        com.google.protobuf.Int32Value.Builder builderForValue) {
      if (targetGroupIndexBuilder_ == null) {
        targetGroupIndex_ = builderForValue.build();
        onChanged();
      } else {
        targetGroupIndexBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public Builder mergeTargetGroupIndex(com.google.protobuf.Int32Value value) {
      if (targetGroupIndexBuilder_ == null) {
        if (targetGroupIndex_ != null) {
          targetGroupIndex_ =
            com.google.protobuf.Int32Value.newBuilder(targetGroupIndex_).mergeFrom(value).buildPartial();
        } else {
          targetGroupIndex_ = value;
        }
        onChanged();
      } else {
        targetGroupIndexBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public Builder clearTargetGroupIndex() {
      if (targetGroupIndexBuilder_ == null) {
        targetGroupIndex_ = null;
        onChanged();
      } else {
        targetGroupIndex_ = null;
        targetGroupIndexBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public com.google.protobuf.Int32Value.Builder getTargetGroupIndexBuilder() {
      
      onChanged();
      return getTargetGroupIndexFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    public com.google.protobuf.Int32ValueOrBuilder getTargetGroupIndexOrBuilder() {
      if (targetGroupIndexBuilder_ != null) {
        return targetGroupIndexBuilder_.getMessageOrBuilder();
      } else {
        return targetGroupIndex_ == null ?
            com.google.protobuf.Int32Value.getDefaultInstance() : targetGroupIndex_;
      }
    }
    /**
     * <code>.google.protobuf.Int32Value target_group_index = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Int32Value, com.google.protobuf.Int32Value.Builder, com.google.protobuf.Int32ValueOrBuilder> 
        getTargetGroupIndexFieldBuilder() {
      if (targetGroupIndexBuilder_ == null) {
        targetGroupIndexBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Int32Value, com.google.protobuf.Int32Value.Builder, com.google.protobuf.Int32ValueOrBuilder>(
                getTargetGroupIndex(),
                getParentForChildren(),
                isClean());
        targetGroupIndex_ = null;
      }
      return targetGroupIndexBuilder_;
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


    // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteRelationshipGroupMemberRequest)
  }

  // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteRelationshipGroupMemberRequest)
  private static final im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest();
  }

  public static im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest>
      PARSER = new com.google.protobuf.AbstractParser<DeleteRelationshipGroupMemberRequest>() {
    @java.lang.Override
    public DeleteRelationshipGroupMemberRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DeleteRelationshipGroupMemberRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public im.turms.turms.pojo.request.DeleteRelationshipGroupMemberRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

