package kr.pokeum.fragile_codegen

import com.squareup.kotlinpoet.*
import kr.pokeum.fragile.element.JsonArray
import kr.pokeum.fragile.element.JsonObject
import kr.pokeum.fragile_codegen.common.Primitive
import kr.pokeum.fragile_codegen.extension.isNullablePrimitive
import kr.pokeum.fragile_codegen.model.ClassBinding
import kr.pokeum.fragile_codegen.model.FieldBinding
import javax.lang.model.type.DeclaredType
import javax.tools.Diagnostic

object CodeGen {

    private const val SERIALIZE_NULL = true

    private val JSON_OBJECT_CLASS_NAME = JsonObject::class.asClassName()
    private val JSON_ARRAY_CLASS_NAME = JsonArray::class.asClassName()
    private const val TO_JSON_OBJECT_FUN_NAME = "toJSONObject"
    private const val JSON_OBJECT_NULL = "JSONObject.NULL"
    private const val SERIALIZE_NULL_TO_STRING_FUN_NAME = "serializeNullToString"

    fun createFileSpec(packageName: String, binding: ClassBinding): FileSpec {
        val builder = FileSpec.builder(packageName, "${binding.getSimpleName()}Stub")
            .addImport(JSON_OBJECT_CLASS_NAME.packageName, JSON_OBJECT_CLASS_NAME.simpleName)
            .addImport(JSON_ARRAY_CLASS_NAME.packageName, JSON_ARRAY_CLASS_NAME.simpleName)
            .addFunction(createToClassObjectFunc(binding))
        return builder.build()
    }

    private fun createToClassObjectFunc(binding: ClassBinding): FunSpec {
        val builder = FunSpec.builder("to${binding.getSimpleName()}")
            .addModifiers(KModifier.INTERNAL)
            .receiver(JsonObject::class)
            .returns(binding.getClassName())
            .addStatement(serializeNullToStringFuncStatement())
            .addStatement("val obj = %L(this)", JSON_OBJECT_CLASS_NAME.simpleName)
            .addStatement("return %L(", binding.getSimpleName())
        //binding.getFieldBindings().forEach { builder.addCode(createToClassObjectCodeBlock(it)) }
        builder.addStatement(")")
        return builder.build()
    }

//    private fun createToClassObjectCodeBlock(binding: FieldBinding): CodeBlock {
//        val builder = CodeBlock.builder()
//        when {
//            binding.isPrimitive() -> {
//                builder.addStatement("obj.opt(%S).toString().${Primitive(binding.getType()).toTypeFunc()},", binding.getKeyName())
//            }
//            binding.isNullablePrimitive() -> {
//                builder.addStatement("obj.opt(%S)?.$SERIALIZE_NULL_TO_STRING_FUN_NAME()?.${Primitive(binding.getType()).toTypeFunc()},", binding.getKeyName())
//            }
//            binding.isDeclared() -> {
//                val declaredType = binding.getType() as DeclaredType
//                when {
//                    binding.isExtendedBy(CharSequence::class.java) -> {
//                        builder.addStatement("obj.optString(%S, null),", binding.getKeyName())  // String
//                    }
//                    binding.isExtendedBy(Collection::class.java) -> {
//                        val elementType = declaredType.typeArguments[0]
//                        // DEBUG
//                        if (elementType.kind.isPrimitive || elementType.isNullablePrimitive()) {
//                            println(Primitive(elementType).toType())
//                        }
//                    }
//                    binding.isExtendedBy(Map::class.java) -> {
//
//                    }
//                    else -> {
//                        val annotation = declaredType.asElement().getAnnotation(Serializable::class.java)
//                        if (annotation == null) {
//                            ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
//                                "Invalid JSON field type provided",
//                                binding.fieldElement)
//                        }
//                    }
//                }
//            }
//        }
//        return builder.build()
//    }

    private fun serializeNullToStringFuncStatement(): String {
        return "fun Any.$SERIALIZE_NULL_TO_STRING_FUN_NAME(): String? = if (this.equals(null)) null else this.toString()";
    }
}