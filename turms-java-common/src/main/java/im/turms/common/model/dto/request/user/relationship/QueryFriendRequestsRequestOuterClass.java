// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/relationship/query_friend_requests_request.proto

package im.turms.common.model.dto.request.user.relationship;

public final class QueryFriendRequestsRequestOuterClass {
  private QueryFriendRequestsRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_QueryFriendRequestsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_QueryFriendRequestsRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n=request/user/relationship/query_friend" +
      "_requests_request.proto\022\016im.turms.proto\032" +
      "\036google/protobuf/wrappers.proto\"l\n\032Query" +
      "FriendRequestsRequest\022\026\n\016are_sent_by_me\030" +
      "\001 \001(\010\0226\n\021last_updated_date\030\002 \001(\0132\033.googl" +
      "e.protobuf.Int64ValueB:\n3im.turms.common" +
      ".model.dto.request.user.relationshipP\001\272\002" +
      "\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        });
    internal_static_im_turms_proto_QueryFriendRequestsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_QueryFriendRequestsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_QueryFriendRequestsRequest_descriptor,
        new java.lang.String[] { "AreSentByMe", "LastUpdatedDate", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
