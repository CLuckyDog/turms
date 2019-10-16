// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dto/message/messages_with_total.proto

package im.turms.turms.pojo.dto;

/**
 * Protobuf type {@code im.turms.proto.MessagesWithTotal}
 */
public  final class MessagesWithTotal extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:im.turms.proto.MessagesWithTotal)
    MessagesWithTotalOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MessagesWithTotal.newBuilder() to construct.
  private MessagesWithTotal(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MessagesWithTotal() {
    chatType_ = 0;
    messages_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MessagesWithTotal();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MessagesWithTotal(
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

            total_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            chatType_ = rawValue;
            break;
          }
          case 24: {

            fromId_ = input.readInt64();
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              messages_ = new java.util.ArrayList<im.turms.turms.pojo.dto.Message>();
              mutable_bitField0_ |= 0x00000001;
            }
            messages_.add(
                input.readMessage(im.turms.turms.pojo.dto.Message.parser(), extensionRegistry));
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
        messages_ = java.util.Collections.unmodifiableList(messages_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return im.turms.turms.pojo.dto.MessagesWithTotalOuterClass.internal_static_im_turms_proto_MessagesWithTotal_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return im.turms.turms.pojo.dto.MessagesWithTotalOuterClass.internal_static_im_turms_proto_MessagesWithTotal_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            im.turms.turms.pojo.dto.MessagesWithTotal.class, im.turms.turms.pojo.dto.MessagesWithTotal.Builder.class);
  }

  public static final int TOTAL_FIELD_NUMBER = 1;
  private int total_;
  /**
   * <code>int32 total = 1;</code>
   * @return The total.
   */
  public int getTotal() {
    return total_;
  }

  public static final int CHAT_TYPE_FIELD_NUMBER = 2;
  private int chatType_;
  /**
   * <code>.im.turms.proto.ChatType chat_type = 2;</code>
   * @return The enum numeric value on the wire for chatType.
   */
  public int getChatTypeValue() {
    return chatType_;
  }
  /**
   * <code>.im.turms.proto.ChatType chat_type = 2;</code>
   * @return The chatType.
   */
  public im.turms.turms.constant.ChatType getChatType() {
    @SuppressWarnings("deprecation")
    im.turms.turms.constant.ChatType result = im.turms.turms.constant.ChatType.valueOf(chatType_);
    return result == null ? im.turms.turms.constant.ChatType.UNRECOGNIZED : result;
  }

  public static final int FROM_ID_FIELD_NUMBER = 3;
  private long fromId_;
  /**
   * <code>int64 from_id = 3;</code>
   * @return The fromId.
   */
  public long getFromId() {
    return fromId_;
  }

  public static final int MESSAGES_FIELD_NUMBER = 4;
  private java.util.List<im.turms.turms.pojo.dto.Message> messages_;
  /**
   * <code>repeated .im.turms.proto.Message messages = 4;</code>
   */
  public java.util.List<im.turms.turms.pojo.dto.Message> getMessagesList() {
    return messages_;
  }
  /**
   * <code>repeated .im.turms.proto.Message messages = 4;</code>
   */
  public java.util.List<? extends im.turms.turms.pojo.dto.MessageOrBuilder> 
      getMessagesOrBuilderList() {
    return messages_;
  }
  /**
   * <code>repeated .im.turms.proto.Message messages = 4;</code>
   */
  public int getMessagesCount() {
    return messages_.size();
  }
  /**
   * <code>repeated .im.turms.proto.Message messages = 4;</code>
   */
  public im.turms.turms.pojo.dto.Message getMessages(int index) {
    return messages_.get(index);
  }
  /**
   * <code>repeated .im.turms.proto.Message messages = 4;</code>
   */
  public im.turms.turms.pojo.dto.MessageOrBuilder getMessagesOrBuilder(
      int index) {
    return messages_.get(index);
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
    if (total_ != 0) {
      output.writeInt32(1, total_);
    }
    if (chatType_ != im.turms.turms.constant.ChatType.PRIVATE.getNumber()) {
      output.writeEnum(2, chatType_);
    }
    if (fromId_ != 0L) {
      output.writeInt64(3, fromId_);
    }
    for (int i = 0; i < messages_.size(); i++) {
      output.writeMessage(4, messages_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (total_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, total_);
    }
    if (chatType_ != im.turms.turms.constant.ChatType.PRIVATE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, chatType_);
    }
    if (fromId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, fromId_);
    }
    for (int i = 0; i < messages_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, messages_.get(i));
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
    if (!(obj instanceof im.turms.turms.pojo.dto.MessagesWithTotal)) {
      return super.equals(obj);
    }
    im.turms.turms.pojo.dto.MessagesWithTotal other = (im.turms.turms.pojo.dto.MessagesWithTotal) obj;

    if (getTotal()
        != other.getTotal()) return false;
    if (chatType_ != other.chatType_) return false;
    if (getFromId()
        != other.getFromId()) return false;
    if (!getMessagesList()
        .equals(other.getMessagesList())) return false;
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
    hash = (37 * hash) + TOTAL_FIELD_NUMBER;
    hash = (53 * hash) + getTotal();
    hash = (37 * hash) + CHAT_TYPE_FIELD_NUMBER;
    hash = (53 * hash) + chatType_;
    hash = (37 * hash) + FROM_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFromId());
    if (getMessagesCount() > 0) {
      hash = (37 * hash) + MESSAGES_FIELD_NUMBER;
      hash = (53 * hash) + getMessagesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static im.turms.turms.pojo.dto.MessagesWithTotal parseFrom(
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
  public static Builder newBuilder(im.turms.turms.pojo.dto.MessagesWithTotal prototype) {
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
   * Protobuf type {@code im.turms.proto.MessagesWithTotal}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:im.turms.proto.MessagesWithTotal)
      im.turms.turms.pojo.dto.MessagesWithTotalOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return im.turms.turms.pojo.dto.MessagesWithTotalOuterClass.internal_static_im_turms_proto_MessagesWithTotal_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return im.turms.turms.pojo.dto.MessagesWithTotalOuterClass.internal_static_im_turms_proto_MessagesWithTotal_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              im.turms.turms.pojo.dto.MessagesWithTotal.class, im.turms.turms.pojo.dto.MessagesWithTotal.Builder.class);
    }

    // Construct using im.turms.turms.pojo.dto.MessagesWithTotal.newBuilder()
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
        getMessagesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      total_ = 0;

      chatType_ = 0;

      fromId_ = 0L;

      if (messagesBuilder_ == null) {
        messages_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        messagesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return im.turms.turms.pojo.dto.MessagesWithTotalOuterClass.internal_static_im_turms_proto_MessagesWithTotal_descriptor;
    }

    @java.lang.Override
    public im.turms.turms.pojo.dto.MessagesWithTotal getDefaultInstanceForType() {
      return im.turms.turms.pojo.dto.MessagesWithTotal.getDefaultInstance();
    }

    @java.lang.Override
    public im.turms.turms.pojo.dto.MessagesWithTotal build() {
      im.turms.turms.pojo.dto.MessagesWithTotal result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public im.turms.turms.pojo.dto.MessagesWithTotal buildPartial() {
      im.turms.turms.pojo.dto.MessagesWithTotal result = new im.turms.turms.pojo.dto.MessagesWithTotal(this);
      int from_bitField0_ = bitField0_;
      result.total_ = total_;
      result.chatType_ = chatType_;
      result.fromId_ = fromId_;
      if (messagesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          messages_ = java.util.Collections.unmodifiableList(messages_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.messages_ = messages_;
      } else {
        result.messages_ = messagesBuilder_.build();
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
      if (other instanceof im.turms.turms.pojo.dto.MessagesWithTotal) {
        return mergeFrom((im.turms.turms.pojo.dto.MessagesWithTotal)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(im.turms.turms.pojo.dto.MessagesWithTotal other) {
      if (other == im.turms.turms.pojo.dto.MessagesWithTotal.getDefaultInstance()) return this;
      if (other.getTotal() != 0) {
        setTotal(other.getTotal());
      }
      if (other.chatType_ != 0) {
        setChatTypeValue(other.getChatTypeValue());
      }
      if (other.getFromId() != 0L) {
        setFromId(other.getFromId());
      }
      if (messagesBuilder_ == null) {
        if (!other.messages_.isEmpty()) {
          if (messages_.isEmpty()) {
            messages_ = other.messages_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureMessagesIsMutable();
            messages_.addAll(other.messages_);
          }
          onChanged();
        }
      } else {
        if (!other.messages_.isEmpty()) {
          if (messagesBuilder_.isEmpty()) {
            messagesBuilder_.dispose();
            messagesBuilder_ = null;
            messages_ = other.messages_;
            bitField0_ = (bitField0_ & ~0x00000001);
            messagesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMessagesFieldBuilder() : null;
          } else {
            messagesBuilder_.addAllMessages(other.messages_);
          }
        }
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
      im.turms.turms.pojo.dto.MessagesWithTotal parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (im.turms.turms.pojo.dto.MessagesWithTotal) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int total_ ;
    /**
     * <code>int32 total = 1;</code>
     * @return The total.
     */
    public int getTotal() {
      return total_;
    }
    /**
     * <code>int32 total = 1;</code>
     * @param value The total to set.
     * @return This builder for chaining.
     */
    public Builder setTotal(int value) {
      
      total_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 total = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTotal() {
      
      total_ = 0;
      onChanged();
      return this;
    }

    private int chatType_ = 0;
    /**
     * <code>.im.turms.proto.ChatType chat_type = 2;</code>
     * @return The enum numeric value on the wire for chatType.
     */
    public int getChatTypeValue() {
      return chatType_;
    }
    /**
     * <code>.im.turms.proto.ChatType chat_type = 2;</code>
     * @param value The enum numeric value on the wire for chatType to set.
     * @return This builder for chaining.
     */
    public Builder setChatTypeValue(int value) {
      chatType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.im.turms.proto.ChatType chat_type = 2;</code>
     * @return The chatType.
     */
    public im.turms.turms.constant.ChatType getChatType() {
      @SuppressWarnings("deprecation")
      im.turms.turms.constant.ChatType result = im.turms.turms.constant.ChatType.valueOf(chatType_);
      return result == null ? im.turms.turms.constant.ChatType.UNRECOGNIZED : result;
    }
    /**
     * <code>.im.turms.proto.ChatType chat_type = 2;</code>
     * @param value The chatType to set.
     * @return This builder for chaining.
     */
    public Builder setChatType(im.turms.turms.constant.ChatType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      chatType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.im.turms.proto.ChatType chat_type = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearChatType() {
      
      chatType_ = 0;
      onChanged();
      return this;
    }

    private long fromId_ ;
    /**
     * <code>int64 from_id = 3;</code>
     * @return The fromId.
     */
    public long getFromId() {
      return fromId_;
    }
    /**
     * <code>int64 from_id = 3;</code>
     * @param value The fromId to set.
     * @return This builder for chaining.
     */
    public Builder setFromId(long value) {
      
      fromId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 from_id = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearFromId() {
      
      fromId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<im.turms.turms.pojo.dto.Message> messages_ =
      java.util.Collections.emptyList();
    private void ensureMessagesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        messages_ = new java.util.ArrayList<im.turms.turms.pojo.dto.Message>(messages_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        im.turms.turms.pojo.dto.Message, im.turms.turms.pojo.dto.Message.Builder, im.turms.turms.pojo.dto.MessageOrBuilder> messagesBuilder_;

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public java.util.List<im.turms.turms.pojo.dto.Message> getMessagesList() {
      if (messagesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(messages_);
      } else {
        return messagesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public int getMessagesCount() {
      if (messagesBuilder_ == null) {
        return messages_.size();
      } else {
        return messagesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.turms.pojo.dto.Message getMessages(int index) {
      if (messagesBuilder_ == null) {
        return messages_.get(index);
      } else {
        return messagesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder setMessages(
        int index, im.turms.turms.pojo.dto.Message value) {
      if (messagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMessagesIsMutable();
        messages_.set(index, value);
        onChanged();
      } else {
        messagesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder setMessages(
        int index, im.turms.turms.pojo.dto.Message.Builder builderForValue) {
      if (messagesBuilder_ == null) {
        ensureMessagesIsMutable();
        messages_.set(index, builderForValue.build());
        onChanged();
      } else {
        messagesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder addMessages(im.turms.turms.pojo.dto.Message value) {
      if (messagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMessagesIsMutable();
        messages_.add(value);
        onChanged();
      } else {
        messagesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder addMessages(
        int index, im.turms.turms.pojo.dto.Message value) {
      if (messagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMessagesIsMutable();
        messages_.add(index, value);
        onChanged();
      } else {
        messagesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder addMessages(
        im.turms.turms.pojo.dto.Message.Builder builderForValue) {
      if (messagesBuilder_ == null) {
        ensureMessagesIsMutable();
        messages_.add(builderForValue.build());
        onChanged();
      } else {
        messagesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder addMessages(
        int index, im.turms.turms.pojo.dto.Message.Builder builderForValue) {
      if (messagesBuilder_ == null) {
        ensureMessagesIsMutable();
        messages_.add(index, builderForValue.build());
        onChanged();
      } else {
        messagesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder addAllMessages(
        java.lang.Iterable<? extends im.turms.turms.pojo.dto.Message> values) {
      if (messagesBuilder_ == null) {
        ensureMessagesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, messages_);
        onChanged();
      } else {
        messagesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder clearMessages() {
      if (messagesBuilder_ == null) {
        messages_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        messagesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public Builder removeMessages(int index) {
      if (messagesBuilder_ == null) {
        ensureMessagesIsMutable();
        messages_.remove(index);
        onChanged();
      } else {
        messagesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.turms.pojo.dto.Message.Builder getMessagesBuilder(
        int index) {
      return getMessagesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.turms.pojo.dto.MessageOrBuilder getMessagesOrBuilder(
        int index) {
      if (messagesBuilder_ == null) {
        return messages_.get(index);  } else {
        return messagesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public java.util.List<? extends im.turms.turms.pojo.dto.MessageOrBuilder> 
         getMessagesOrBuilderList() {
      if (messagesBuilder_ != null) {
        return messagesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(messages_);
      }
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.turms.pojo.dto.Message.Builder addMessagesBuilder() {
      return getMessagesFieldBuilder().addBuilder(
          im.turms.turms.pojo.dto.Message.getDefaultInstance());
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.turms.pojo.dto.Message.Builder addMessagesBuilder(
        int index) {
      return getMessagesFieldBuilder().addBuilder(
          index, im.turms.turms.pojo.dto.Message.getDefaultInstance());
    }
    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public java.util.List<im.turms.turms.pojo.dto.Message.Builder> 
         getMessagesBuilderList() {
      return getMessagesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        im.turms.turms.pojo.dto.Message, im.turms.turms.pojo.dto.Message.Builder, im.turms.turms.pojo.dto.MessageOrBuilder> 
        getMessagesFieldBuilder() {
      if (messagesBuilder_ == null) {
        messagesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            im.turms.turms.pojo.dto.Message, im.turms.turms.pojo.dto.Message.Builder, im.turms.turms.pojo.dto.MessageOrBuilder>(
                messages_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        messages_ = null;
      }
      return messagesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:im.turms.proto.MessagesWithTotal)
  }

  // @@protoc_insertion_point(class_scope:im.turms.proto.MessagesWithTotal)
  private static final im.turms.turms.pojo.dto.MessagesWithTotal DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new im.turms.turms.pojo.dto.MessagesWithTotal();
  }

  public static im.turms.turms.pojo.dto.MessagesWithTotal getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MessagesWithTotal>
      PARSER = new com.google.protobuf.AbstractParser<MessagesWithTotal>() {
    @java.lang.Override
    public MessagesWithTotal parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MessagesWithTotal(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MessagesWithTotal> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MessagesWithTotal> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public im.turms.turms.pojo.dto.MessagesWithTotal getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

