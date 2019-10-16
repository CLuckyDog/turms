// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/relationship/update_relationship_group_request.proto

package im.turms.turms.pojo.request;

/**
 * Protobuf type {@code im.turms.proto.UpdateRelationshipGroupRequest}
 */
public  final class UpdateRelationshipGroupRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateRelationshipGroupRequest)
    UpdateRelationshipGroupRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UpdateRelationshipGroupRequest.newBuilder() to construct.
  private UpdateRelationshipGroupRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateRelationshipGroupRequest() {
    newName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UpdateRelationshipGroupRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdateRelationshipGroupRequest(
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

            groupIndex_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            newName_ = s;
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
    return im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipGroupRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipGroupRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.class, im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.Builder.class);
  }

  public static final int GROUP_INDEX_FIELD_NUMBER = 1;
  private int groupIndex_;
  /**
   * <code>int32 group_index = 1;</code>
   * @return The groupIndex.
   */
  public int getGroupIndex() {
    return groupIndex_;
  }

  public static final int NEW_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object newName_;
  /**
   * <code>string new_name = 2;</code>
   * @return The newName.
   */
  public java.lang.String getNewName() {
    java.lang.Object ref = newName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      newName_ = s;
      return s;
    }
  }
  /**
   * <code>string new_name = 2;</code>
   * @return The bytes for newName.
   */
  public com.google.protobuf.ByteString
      getNewNameBytes() {
    java.lang.Object ref = newName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      newName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (groupIndex_ != 0) {
      output.writeInt32(1, groupIndex_);
    }
    if (!getNewNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, newName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (groupIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, groupIndex_);
    }
    if (!getNewNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, newName_);
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
    if (!(obj instanceof im.turms.turms.pojo.request.UpdateRelationshipGroupRequest)) {
      return super.equals(obj);
    }
    im.turms.turms.pojo.request.UpdateRelationshipGroupRequest other = (im.turms.turms.pojo.request.UpdateRelationshipGroupRequest) obj;

    if (getGroupIndex()
        != other.getGroupIndex()) return false;
    if (!getNewName()
        .equals(other.getNewName())) return false;
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
    hash = (37 * hash) + GROUP_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getGroupIndex();
    hash = (37 * hash) + NEW_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getNewName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parseFrom(
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
  public static Builder newBuilder(im.turms.turms.pojo.request.UpdateRelationshipGroupRequest prototype) {
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
   * Protobuf type {@code im.turms.proto.UpdateRelationshipGroupRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateRelationshipGroupRequest)
      im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipGroupRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipGroupRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.class, im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.Builder.class);
    }

    // Construct using im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.newBuilder()
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
      groupIndex_ = 0;

      newName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return im.turms.turms.pojo.request.UpdateRelationshipGroupRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipGroupRequest_descriptor;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.UpdateRelationshipGroupRequest getDefaultInstanceForType() {
      return im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.getDefaultInstance();
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.UpdateRelationshipGroupRequest build() {
      im.turms.turms.pojo.request.UpdateRelationshipGroupRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public im.turms.turms.pojo.request.UpdateRelationshipGroupRequest buildPartial() {
      im.turms.turms.pojo.request.UpdateRelationshipGroupRequest result = new im.turms.turms.pojo.request.UpdateRelationshipGroupRequest(this);
      result.groupIndex_ = groupIndex_;
      result.newName_ = newName_;
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
      if (other instanceof im.turms.turms.pojo.request.UpdateRelationshipGroupRequest) {
        return mergeFrom((im.turms.turms.pojo.request.UpdateRelationshipGroupRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(im.turms.turms.pojo.request.UpdateRelationshipGroupRequest other) {
      if (other == im.turms.turms.pojo.request.UpdateRelationshipGroupRequest.getDefaultInstance()) return this;
      if (other.getGroupIndex() != 0) {
        setGroupIndex(other.getGroupIndex());
      }
      if (!other.getNewName().isEmpty()) {
        newName_ = other.newName_;
        onChanged();
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
      im.turms.turms.pojo.request.UpdateRelationshipGroupRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (im.turms.turms.pojo.request.UpdateRelationshipGroupRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int groupIndex_ ;
    /**
     * <code>int32 group_index = 1;</code>
     * @return The groupIndex.
     */
    public int getGroupIndex() {
      return groupIndex_;
    }
    /**
     * <code>int32 group_index = 1;</code>
     * @param value The groupIndex to set.
     * @return This builder for chaining.
     */
    public Builder setGroupIndex(int value) {
      
      groupIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 group_index = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGroupIndex() {
      
      groupIndex_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object newName_ = "";
    /**
     * <code>string new_name = 2;</code>
     * @return The newName.
     */
    public java.lang.String getNewName() {
      java.lang.Object ref = newName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        newName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string new_name = 2;</code>
     * @return The bytes for newName.
     */
    public com.google.protobuf.ByteString
        getNewNameBytes() {
      java.lang.Object ref = newName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        newName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string new_name = 2;</code>
     * @param value The newName to set.
     * @return This builder for chaining.
     */
    public Builder setNewName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      newName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string new_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearNewName() {
      
      newName_ = getDefaultInstance().getNewName();
      onChanged();
      return this;
    }
    /**
     * <code>string new_name = 2;</code>
     * @param value The bytes for newName to set.
     * @return This builder for chaining.
     */
    public Builder setNewNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      newName_ = value;
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


    // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateRelationshipGroupRequest)
  }

  // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateRelationshipGroupRequest)
  private static final im.turms.turms.pojo.request.UpdateRelationshipGroupRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new im.turms.turms.pojo.request.UpdateRelationshipGroupRequest();
  }

  public static im.turms.turms.pojo.request.UpdateRelationshipGroupRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateRelationshipGroupRequest>
      PARSER = new com.google.protobuf.AbstractParser<UpdateRelationshipGroupRequest>() {
    @java.lang.Override
    public UpdateRelationshipGroupRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UpdateRelationshipGroupRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateRelationshipGroupRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateRelationshipGroupRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public im.turms.turms.pojo.request.UpdateRelationshipGroupRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

