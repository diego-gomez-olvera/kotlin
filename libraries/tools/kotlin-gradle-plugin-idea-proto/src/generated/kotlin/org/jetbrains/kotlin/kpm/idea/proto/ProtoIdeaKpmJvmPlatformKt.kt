//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: proto_idea_kpm.proto

package org.jetbrains.kotlin.kpm.idea.proto;

@kotlin.jvm.JvmSynthetic
internal inline fun protoIdeaKpmJvmPlatform(block: org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatformKt.Dsl.() -> kotlin.Unit): org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform =
  org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatformKt.Dsl._create(org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform.newBuilder()).apply { block() }._build()
internal object ProtoIdeaKpmJvmPlatformKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  internal class Dsl private constructor(
    private val _builder: org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform.Builder
  ) {
    internal companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform = _builder.build()

    /**
     * <code>optional .org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmExtras extras = 1;</code>
     */
    internal var extras: org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmExtras
      @JvmName("getExtras")
      get() = _builder.getExtras()
      @JvmName("setExtras")
      set(value) {
        _builder.setExtras(value)
      }
    /**
     * <code>optional .org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmExtras extras = 1;</code>
     */
    internal fun clearExtras() {
      _builder.clearExtras()
    }
    /**
     * <code>optional .org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmExtras extras = 1;</code>
     * @return Whether the extras field is set.
     */
    internal fun hasExtras(): kotlin.Boolean {
      return _builder.hasExtras()
    }

    /**
     * <code>string jvm_target = 2;</code>
     */
    internal var jvmTarget: kotlin.String
      @JvmName("getJvmTarget")
      get() = _builder.getJvmTarget()
      @JvmName("setJvmTarget")
      set(value) {
        _builder.setJvmTarget(value)
      }
    /**
     * <code>string jvm_target = 2;</code>
     */
    internal fun clearJvmTarget() {
      _builder.clearJvmTarget()
    }
  }
}
@kotlin.jvm.JvmSynthetic
internal inline fun org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform.copy(block: org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatformKt.Dsl.() -> kotlin.Unit): org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatform =
  org.jetbrains.kotlin.kpm.idea.proto.ProtoIdeaKpmJvmPlatformKt.Dsl._create(this.toBuilder()).apply { block() }._build()
