// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/file/image_file.proto

package im.turms.turms.pojo.bo.file;

public final class ImageFileOuterClass {
  private ImageFileOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_ImageFile_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_ImageFile_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_ImageFile_Description_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_ImageFile_Description_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033model/file/image_file.proto\022\016im.turms." +
      "proto\032\036google/protobuf/wrappers.proto\"\236\002" +
      "\n\tImageFile\022:\n\013description\030\001 \001(\0132%.im.tu" +
      "rms.proto.ImageFile.Description\022)\n\004data\030" +
      "\002 \001(\0132\033.google.protobuf.BytesValue\032\251\001\n\013D" +
      "escription\022\013\n\003url\030\001 \001(\t\022,\n\010original\030\002 \001(" +
      "\0132\032.google.protobuf.BoolValue\022/\n\nimage_s" +
      "ize\030\003 \001(\0132\033.google.protobuf.Int32Value\022." +
      "\n\tfile_size\030\004 \001(\0132\033.google.protobuf.Int3" +
      "2ValueB\037\n\033im.turms.turms.pojo.bo.fileP\001b" +
      "\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        });
    internal_static_im_turms_proto_ImageFile_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_ImageFile_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_ImageFile_descriptor,
        new java.lang.String[] { "Description", "Data", });
    internal_static_im_turms_proto_ImageFile_Description_descriptor =
      internal_static_im_turms_proto_ImageFile_descriptor.getNestedTypes().get(0);
    internal_static_im_turms_proto_ImageFile_Description_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_ImageFile_Description_descriptor,
        new java.lang.String[] { "Url", "Original", "ImageSize", "FileSize", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
