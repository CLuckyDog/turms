// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/message/update_typing_status_request.proto

package im.turms.common.model.dto.request.message;

public final class UpdateTypingStatusRequestOuterClass {
  private UpdateTypingStatusRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_UpdateTypingStatusRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n2request/message/update_typing_status_r" +
      "equest.proto\022\016im.turms.proto\"D\n\031UpdateTy" +
      "pingStatusRequest\022\030\n\020is_group_message\030\001 " +
      "\001(\010\022\r\n\005to_id\030\002 \001(\003B0\n)im.turms.common.mo" +
      "del.dto.request.messageP\001\272\002\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_UpdateTypingStatusRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor,
        new java.lang.String[] { "IsGroupMessage", "ToId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
