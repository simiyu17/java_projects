// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: guessnumber.proto

package org.grpc.sample.server;

public interface HowLuckyResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:HowLuckyResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool isLucky = 1;</code>
   * @return The isLucky.
   */
  boolean getIsLucky();

  /**
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}