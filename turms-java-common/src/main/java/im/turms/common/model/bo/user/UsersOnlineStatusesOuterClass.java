// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/user/users_online_statuses.proto

package im.turms.common.model.bo.user;

public final class UsersOnlineStatusesOuterClass {
  private UsersOnlineStatusesOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_UsersOnlineStatuses_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_UsersOnlineStatuses_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n&model/user/users_online_statuses.proto" +
      "\022\016im.turms.proto\032#model/user/user_status" +
      "_detail.proto\"N\n\023UsersOnlineStatuses\0227\n\r" +
      "user_statuses\030\001 \003(\0132 .im.turms.proto.Use" +
      "rStatusDetailB$\n\035im.turms.common.model.b" +
      "o.userP\001\272\002\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          im.turms.common.model.bo.user.UserStatusDetailOuterClass.getDescriptor(),
        });
    internal_static_im_turms_proto_UsersOnlineStatuses_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_UsersOnlineStatuses_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_UsersOnlineStatuses_descriptor,
        new java.lang.String[] { "UserStatuses", });
    im.turms.common.model.bo.user.UserStatusDetailOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
