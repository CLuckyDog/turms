// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/group/member/query_group_members_request.proto

package im.turms.turms.pojo.request;

/**
 * Protobuf type {@code im.turms.proto.QueryGroupMembersRequest}
 */
public  final class QueryGroupMembersRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:im.turms.proto.QueryGroupMembersRequest)
    QueryGroupMembersRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueryGroupMembersRequest.newBuilder() to construct.
  private QueryGroupMembersRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryGroupMembersRequest() {
    groupMembersIds_ = emptyLongList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QueryGroupMembersRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryGroupMembersRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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

            groupId_ = input.readInt64();
            break;
          }
          case 18: {
            com.google.protobuf.Int64Value.Builder subBuilder = null;
            if (lastUpdatedDate_ != null) {
              subBuilder = lastUpdatedDate_.toBuilder();
            }
            lastUpdatedDate_ = input.readMessage(com.google.protobuf.Int64Value.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(lastUpdatedDate_);
              lastUpdatedDate_ = subBuilder.buildPartial();
            }

            break;
          }
          case 24: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              groupMembersIds_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            groupMembersIds_.addLong(input.readInt64());
            break;
          }
          case 26: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              groupMembersIds_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              groupMembersIds_.addLong(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
          case 34: {
            com.google.protobuf.BoolValue.Builder subBuilder = null;
            if (withStatus_ != null) {
              subBuilder = withStatus_.toBuilder();
            }
            withStatus_ = input.readMessage(com.google.protobuf.BoolValue.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(withStatus_);
              withStatus_ = subBuilder.buildPartial();
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        groupMembersIds_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return im.turms.turms.pojo.request.QueryGroupMembersRequestOuterClass.internal_static_im_turms_proto_QueryGroupMembersRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return im.turms.turms.pojo.request.QueryGroupMembersRequestOuterClass.internal_static_im_turms_proto_QueryGroupMembersRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            im.turms.turms.pojo.request.QueryGroupMembersRequest.class, im.turms.turms.pojo.request.QueryGroupMembersRequest.Builder.class);
  }

  public static final int GROUP_ID_FIELD_NUMBER = 1;
  private long groupId_;
  /**
   * <code>int64 group_id = 1;</code>
   * @return The groupId.
   */
  public long getGroupId() {
    return groupId_;
  }

  public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
  private com.google.protobuf.Int64Value lastUpdatedDate_;
  /**
   * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
   * @return Whether the lastUpdatedDate field is set.
   */
  public boolean hasLastUpdatedDate() {
    return lastUpdatedDate_ != null;
  }
  /**
   * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
   * @return The lastUpdatedDate.
   */
  public com.google.protobuf.Int64Value getLastUpdatedDate() {
    return lastUpdatedDate_ == null ? com.google.protobuf.Int64Value.getDefaultInstance() : lastUpdatedDate_;
  }
  /**
   * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
   */
  public com.google.protobuf.Int64ValueOrBuilder getLastUpdatedDateOrBuilder() {
    return getLastUpdatedDate();
  }

  public static final int GROUP_MEMBERS_IDS_FIELD_NUMBER = 3;
  private com.google.protobuf.Internal.LongList groupMembersIds_;
  /**
   * <code>repeated int64 group_members_ids = 3;</code>
   * @return A list containing the groupMembersIds.
   */
  public java.util.List<java.lang.Long>
      getGroupMembersIdsList() {
    return groupMembersIds_;
  }
  /**
   * <code>repeated int64 group_members_ids = 3;</code>
   * @return The count of groupMembersIds.
   */
  public int getGroupMembersIdsCount() {
    return groupMembersIds_.size();
  }
  /**
   * <code>repeated int64 group_members_ids = 3;</code>
   * @param index The index of the element to return.
   * @return The groupMembersIds at the given index.
   */
  public long getGroupMembersIds(int index) {
    return groupMembersIds_.getLong(index);
  }
  private int groupMembersIdsMemoizedSerializedSize = -1;

  public static final int WITH_STATUS_FIELD_NUMBER = 4;
  private com.google.protobuf.BoolValue withStatus_;
  /**
   * <code>.google.protobuf.BoolValue with_status = 4;</code>
   * @return Whether the withStatus field is set.
   */
  public boolean hasWithStatus() {
    return withStatus_ != null;
  }
  /**
   * <code>.google.protobuf.BoolValue with_status = 4;</code>
   * @return The withStatus.
   */
  public com.google.protobuf.BoolValue getWithStatus() {
    return withStatus_ == null ? com.google.protobuf.BoolValue.getDefaultInstance() : withStatus_;
  }
  /**
   * <code>.google.protobuf.BoolValue with_status = 4;</code>
   */
  public com.google.protobuf.BoolValueOrBuilder getWithStatusOrBuilder() {
    return getWithStatus();
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
    getSerializedSize();
    if (groupId_ != 0L) {
      output.writeInt64(1, groupId_);
    }
    if (lastUpdatedDate_ != null) {
      output.writeMessage(2, getLastUpdatedDate());
    }
    if (getGroupMembersIdsList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(groupMembersIdsMemoizedSerializedSize);
    }
    for (int i = 0; i < groupMembersIds_.size(); i++) {
      output.writeInt64NoTag(groupMembersIds_.getLong(i));
    }
    if (withStatus_ != null) {
      output.writeMessage(4, getWithStatus());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (groupId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, groupId_);
    }
    if (lastUpdatedDate_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getLastUpdatedDate());
    }
    {
      int dataSize = 0;
      for (int i = 0; i < groupMembersIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(groupMembersIds_.getLong(i));
      }
      size += dataSize;
      if (!getGroupMembersIdsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      groupMembersIdsMemoizedSerializedSize = dataSize;
    }
    if (withStatus_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getWithStatus());
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
    if (!(obj instanceof im.turms.turms.pojo.request.QueryGroupMembersRequest)) {
      return super.equals(obj);
    }
    im.turms.turms.pojo.request.QueryGroupMembersRequest other = (im.turms.turms.pojo.request.QueryGroupMembersRequest) obj;

    if (getGroupId()
        != other.getGroupId()) return false;
    if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) return false;
    if (hasLastUpdatedDate()) {
      if (!getLastUpdatedDate()
          .equals(other.getLastUpdatedDate())) return false;
    }
    if (!getGroupMembersIdsList()
        .equals(other.getGroupMembersIdsList())) return false;
    if (hasWithStatus() != other.hasWithStatus()) return false;
    if (hasWithStatus()) {
      if (!getWithStatus()
          .equals(other.getWithStatus())) return false;
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
    hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGroupId());
    if (hasLastUpdatedDate()) {
      hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
      hash = (53 * hash) + getLastUpdatedDate().hashCode();
    }
    if (getGroupMembersIdsCount() > 0) {
      hash = (37 * hash) + GROUP_MEMBERS_IDS_FIELD_NUMBER;
      hash = (53 * hash) + getGroupMembersIdsList().hashCode();
    }
    if (hasWithStatus()) {
      hash = (37 * hash) + WITH_STATUS_FIELD_NUMBER;
      hash = (53 * hash) + getWithStatus().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.QueryGroupMembersRequest parseFrom(
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
  public static Builder newBuilder(im.turms.turms.pojo.request.QueryGroupMembersRequest prototype) {
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
   * Protobuf type {@code im.turms.proto.QueryGroupMembersRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryGroupMembersRequest)
      im.turms.turms.pojo.request.QueryGroupMembersRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return im.turms.turms.pojo.request.QueryGroupMembersRequestOuterClass.internal_static_im_turms_proto_QueryGroupMembersRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return im.turms.turms.pojo.request.QueryGroupMembersRequestOuterClass.internal_static_im_turms_proto_QueryGroupMembersRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              im.turms.turms.pojo.request.QueryGroupMembersRequest.class, im.turms.turms.pojo.request.QueryGroupMembersRequest.Builder.class);
    }

    // Construct using im.turms.turms.pojo.request.QueryGroupMembersRequest.newBuilder()
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
      groupId_ = 0L;

      if (lastUpdatedDateBuilder_ == null) {
        lastUpdatedDate_ = null;
      } else {
        lastUpdatedDate_ = null;
        lastUpdatedDateBuilder_ = null;
      }
      groupMembersIds_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
      if (withStatusBuilder_ == null) {
        withStatus_ = null;
      } else {
        withStatus_ = null;
        withStatusBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return im.turms.turms.pojo.request.QueryGroupMembersRequestOuterClass.internal_static_im_turms_proto_QueryGroupMembersRequest_descriptor;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryGroupMembersRequest getDefaultInstanceForType() {
      return im.turms.turms.pojo.request.QueryGroupMembersRequest.getDefaultInstance();
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryGroupMembersRequest build() {
      im.turms.turms.pojo.request.QueryGroupMembersRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.QueryGroupMembersRequest buildPartial() {
      im.turms.turms.pojo.request.QueryGroupMembersRequest result = new im.turms.turms.pojo.request.QueryGroupMembersRequest(this);
      int from_bitField0_ = bitField0_;
      result.groupId_ = groupId_;
      if (lastUpdatedDateBuilder_ == null) {
        result.lastUpdatedDate_ = lastUpdatedDate_;
      } else {
        result.lastUpdatedDate_ = lastUpdatedDateBuilder_.build();
      }
      if (((bitField0_ & 0x00000001) != 0)) {
        groupMembersIds_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.groupMembersIds_ = groupMembersIds_;
      if (withStatusBuilder_ == null) {
        result.withStatus_ = withStatus_;
      } else {
        result.withStatus_ = withStatusBuilder_.build();
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
      if (other instanceof im.turms.turms.pojo.request.QueryGroupMembersRequest) {
        return mergeFrom((im.turms.turms.pojo.request.QueryGroupMembersRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(im.turms.turms.pojo.request.QueryGroupMembersRequest other) {
      if (other == im.turms.turms.pojo.request.QueryGroupMembersRequest.getDefaultInstance()) return this;
      if (other.getGroupId() != 0L) {
        setGroupId(other.getGroupId());
      }
      if (other.hasLastUpdatedDate()) {
        mergeLastUpdatedDate(other.getLastUpdatedDate());
      }
      if (!other.groupMembersIds_.isEmpty()) {
        if (groupMembersIds_.isEmpty()) {
          groupMembersIds_ = other.groupMembersIds_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureGroupMembersIdsIsMutable();
          groupMembersIds_.addAll(other.groupMembersIds_);
        }
        onChanged();
      }
      if (other.hasWithStatus()) {
        mergeWithStatus(other.getWithStatus());
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
      im.turms.turms.pojo.request.QueryGroupMembersRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (im.turms.turms.pojo.request.QueryGroupMembersRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long groupId_ ;
    /**
     * <code>int64 group_id = 1;</code>
     * @return The groupId.
     */
    public long getGroupId() {
      return groupId_;
    }
    /**
     * <code>int64 group_id = 1;</code>
     * @param value The groupId to set.
     * @return This builder for chaining.
     */
    public Builder setGroupId(long value) {
      
      groupId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 group_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGroupId() {
      
      groupId_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.Int64Value lastUpdatedDate_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Int64Value, com.google.protobuf.Int64Value.Builder, com.google.protobuf.Int64ValueOrBuilder> lastUpdatedDateBuilder_;
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     * @return Whether the lastUpdatedDate field is set.
     */
    public boolean hasLastUpdatedDate() {
      return lastUpdatedDateBuilder_ != null || lastUpdatedDate_ != null;
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     * @return The lastUpdatedDate.
     */
    public com.google.protobuf.Int64Value getLastUpdatedDate() {
      if (lastUpdatedDateBuilder_ == null) {
        return lastUpdatedDate_ == null ? com.google.protobuf.Int64Value.getDefaultInstance() : lastUpdatedDate_;
      } else {
        return lastUpdatedDateBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public Builder setLastUpdatedDate(com.google.protobuf.Int64Value value) {
      if (lastUpdatedDateBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        lastUpdatedDate_ = value;
        onChanged();
      } else {
        lastUpdatedDateBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public Builder setLastUpdatedDate(
        com.google.protobuf.Int64Value.Builder builderForValue) {
      if (lastUpdatedDateBuilder_ == null) {
        lastUpdatedDate_ = builderForValue.build();
        onChanged();
      } else {
        lastUpdatedDateBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public Builder mergeLastUpdatedDate(com.google.protobuf.Int64Value value) {
      if (lastUpdatedDateBuilder_ == null) {
        if (lastUpdatedDate_ != null) {
          lastUpdatedDate_ =
            com.google.protobuf.Int64Value.newBuilder(lastUpdatedDate_).mergeFrom(value).buildPartial();
        } else {
          lastUpdatedDate_ = value;
        }
        onChanged();
      } else {
        lastUpdatedDateBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public Builder clearLastUpdatedDate() {
      if (lastUpdatedDateBuilder_ == null) {
        lastUpdatedDate_ = null;
        onChanged();
      } else {
        lastUpdatedDate_ = null;
        lastUpdatedDateBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public com.google.protobuf.Int64Value.Builder getLastUpdatedDateBuilder() {
      
      onChanged();
      return getLastUpdatedDateFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    public com.google.protobuf.Int64ValueOrBuilder getLastUpdatedDateOrBuilder() {
      if (lastUpdatedDateBuilder_ != null) {
        return lastUpdatedDateBuilder_.getMessageOrBuilder();
      } else {
        return lastUpdatedDate_ == null ?
            com.google.protobuf.Int64Value.getDefaultInstance() : lastUpdatedDate_;
      }
    }
    /**
     * <code>.google.protobuf.Int64Value last_updated_date = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Int64Value, com.google.protobuf.Int64Value.Builder, com.google.protobuf.Int64ValueOrBuilder> 
        getLastUpdatedDateFieldBuilder() {
      if (lastUpdatedDateBuilder_ == null) {
        lastUpdatedDateBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Int64Value, com.google.protobuf.Int64Value.Builder, com.google.protobuf.Int64ValueOrBuilder>(
                getLastUpdatedDate(),
                getParentForChildren(),
                isClean());
        lastUpdatedDate_ = null;
      }
      return lastUpdatedDateBuilder_;
    }

    private com.google.protobuf.Internal.LongList groupMembersIds_ = emptyLongList();
    private void ensureGroupMembersIdsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        groupMembersIds_ = mutableCopy(groupMembersIds_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @return A list containing the groupMembersIds.
     */
    public java.util.List<java.lang.Long>
        getGroupMembersIdsList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(groupMembersIds_) : groupMembersIds_;
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @return The count of groupMembersIds.
     */
    public int getGroupMembersIdsCount() {
      return groupMembersIds_.size();
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @param index The index of the element to return.
     * @return The groupMembersIds at the given index.
     */
    public long getGroupMembersIds(int index) {
      return groupMembersIds_.getLong(index);
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @param index The index to set the value at.
     * @param value The groupMembersIds to set.
     * @return This builder for chaining.
     */
    public Builder setGroupMembersIds(
        int index, long value) {
      ensureGroupMembersIdsIsMutable();
      groupMembersIds_.setLong(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @param value The groupMembersIds to add.
     * @return This builder for chaining.
     */
    public Builder addGroupMembersIds(long value) {
      ensureGroupMembersIdsIsMutable();
      groupMembersIds_.addLong(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @param values The groupMembersIds to add.
     * @return This builder for chaining.
     */
    public Builder addAllGroupMembersIds(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureGroupMembersIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, groupMembersIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 group_members_ids = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearGroupMembersIds() {
      groupMembersIds_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private com.google.protobuf.BoolValue withStatus_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.BoolValue, com.google.protobuf.BoolValue.Builder, com.google.protobuf.BoolValueOrBuilder> withStatusBuilder_;
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     * @return Whether the withStatus field is set.
     */
    public boolean hasWithStatus() {
      return withStatusBuilder_ != null || withStatus_ != null;
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     * @return The withStatus.
     */
    public com.google.protobuf.BoolValue getWithStatus() {
      if (withStatusBuilder_ == null) {
        return withStatus_ == null ? com.google.protobuf.BoolValue.getDefaultInstance() : withStatus_;
      } else {
        return withStatusBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public Builder setWithStatus(com.google.protobuf.BoolValue value) {
      if (withStatusBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        withStatus_ = value;
        onChanged();
      } else {
        withStatusBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public Builder setWithStatus(
        com.google.protobuf.BoolValue.Builder builderForValue) {
      if (withStatusBuilder_ == null) {
        withStatus_ = builderForValue.build();
        onChanged();
      } else {
        withStatusBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public Builder mergeWithStatus(com.google.protobuf.BoolValue value) {
      if (withStatusBuilder_ == null) {
        if (withStatus_ != null) {
          withStatus_ =
            com.google.protobuf.BoolValue.newBuilder(withStatus_).mergeFrom(value).buildPartial();
        } else {
          withStatus_ = value;
        }
        onChanged();
      } else {
        withStatusBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public Builder clearWithStatus() {
      if (withStatusBuilder_ == null) {
        withStatus_ = null;
        onChanged();
      } else {
        withStatus_ = null;
        withStatusBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public com.google.protobuf.BoolValue.Builder getWithStatusBuilder() {
      
      onChanged();
      return getWithStatusFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    public com.google.protobuf.BoolValueOrBuilder getWithStatusOrBuilder() {
      if (withStatusBuilder_ != null) {
        return withStatusBuilder_.getMessageOrBuilder();
      } else {
        return withStatus_ == null ?
            com.google.protobuf.BoolValue.getDefaultInstance() : withStatus_;
      }
    }
    /**
     * <code>.google.protobuf.BoolValue with_status = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.BoolValue, com.google.protobuf.BoolValue.Builder, com.google.protobuf.BoolValueOrBuilder> 
        getWithStatusFieldBuilder() {
      if (withStatusBuilder_ == null) {
        withStatusBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.BoolValue, com.google.protobuf.BoolValue.Builder, com.google.protobuf.BoolValueOrBuilder>(
                getWithStatus(),
                getParentForChildren(),
                isClean());
        withStatus_ = null;
      }
      return withStatusBuilder_;
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


    // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryGroupMembersRequest)
  }

  // @@protoc_insertion_point(class_scope:im.turms.proto.QueryGroupMembersRequest)
  private static final im.turms.turms.pojo.request.QueryGroupMembersRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new im.turms.turms.pojo.request.QueryGroupMembersRequest();
  }

  public static im.turms.turms.pojo.request.QueryGroupMembersRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryGroupMembersRequest>
      PARSER = new com.google.protobuf.AbstractParser<QueryGroupMembersRequest>() {
    @java.lang.Override
    public QueryGroupMembersRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QueryGroupMembersRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryGroupMembersRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryGroupMembersRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public im.turms.turms.pojo.request.QueryGroupMembersRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

