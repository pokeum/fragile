package kr.pokeum.fragile_codegen

import com.squareup.kotlinpoet.*
import kr.pokeum.fragile.annotation.SerializedName
import kr.pokeum.fragile.element.JsonArray
import kr.pokeum.fragile.element.JsonObject
import kr.pokeum.fragile.util.JavaConverter
import kr.pokeum.fragile_codegen.common.Primitive
import kr.pokeum.fragile_codegen.model.ClassBinding
import kr.pokeum.fragile_codegen.model.FieldBinding
import javax.lang.model.type.DeclaredType
import javax.tools.Diagnostic

object CodeGen {

    private val JAVA_CONVERTER_CLASS_NAME = JavaConverter::class.asClassName()
    private val JSON_OBJECT_CLASS_NAME = JsonObject::class.asClassName()
    private val JSON_ARRAY_CLASS_NAME = JsonArray::class.asClassName()

    fun createFileSpec(packageName: String, binding: ClassBinding): FileSpec {
        val builder = FileSpec.builder(packageName, "${binding.getSimpleName()}Stub")
            .addImport(JAVA_CONVERTER_CLASS_NAME.packageName, JAVA_CONVERTER_CLASS_NAME.simpleName)
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
            .addStatement("")
            .addStatement("return %L(", binding.getSimpleName())
        binding.getFieldBindings().forEach { builder.addCode(createToClassObjectCodeBlock(it)) }
        builder.addStatement(")")
        return builder.build()
    }

    private fun createToClassObjectCodeBlock(binding: FieldBinding): CodeBlock {
        val builder = CodeBlock.builder()
        when {
            binding.isPrimitive() -> {
                builder.addStatement(
                    "\t%L = get(%S).toString().${Primitive(binding.getType()).toTypeFunc()},",
                    binding.getFieldName(),
                    binding.getKeyName()
                )
            }
            binding.isNullablePrimitive() -> {
                builder.addStatement(
                    "\t%L = get(%S)?.toString()?.${Primitive(binding.getType()).toTypeFunc()},",
                    binding.getFieldName(),
                    binding.getKeyName()
                )
            }
            binding.isDeclared() -> {
                val declaredType = binding.getType() as DeclaredType
                when {
                    binding.isExtendedBy(CharSequence::class.java) -> {
                        builder.addStatement(
                            "\t%L = JavaConverter.convertString(get(%S)?.toString()),",
                            binding.getFieldName(),
                            binding.getKeyName()
                        )
                    }
//                    binding.isExtendedBy(Collection::class.java) -> {
//                        val elementType = declaredType.typeArguments[0]
//                        // DEBUG
//                        println(">>> ${elementType.asTypeName()}")
//                    }
//                    binding.isExtendedBy(Map::class.java) -> {
//                    }
                    else -> {
                        val annotation = declaredType.asElement().getAnnotation(SerializedName::class.java)
                        if (annotation == null) {
                            ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                                "Invalid JSON field type provided",
                                binding.fieldElement)
                        }
                    }
                }
            }
        }
        return builder.build()
    }
}