// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/message/query_pending_messages_with_total_request.proto

package im.turms.common.model.dto.request.message;

public final class QueryPendingMessagesWithTotalRequestOuterClass {
  private QueryPendingMessagesWithTotalRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_QueryPendingMessagesWithTotalRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_QueryPendingMessagesWithTotalRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n?request/message/query_pending_messages" +
      "_with_total_request.proto\022\016im.turms.prot" +
      "o\032\036google/protobuf/wrappers.proto\"Q\n$Que" +
      "ryPendingMessagesWithTotalRequest\022)\n\004siz" +
      "e\030\001 \001(\0132\033.google.protobuf.Int32ValueB0\n)" +
      "im.turms.common.model.dto.request.messag" +
      "eP\001\272\002\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        });
    internal_static_im_turms_proto_QueryPendingMessagesWithTotalRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_QueryPendingMessagesWithTotalRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_QueryPendingMessagesWithTotalRequest_descriptor,
        new java.lang.String[] { "Size", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
