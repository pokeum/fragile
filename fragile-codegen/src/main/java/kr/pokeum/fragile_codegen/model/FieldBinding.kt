package kr.pokeum.fragile_codegen.model

import kotlin.reflect.KClass
import kr.pokeum.fragile.annotation.SerializedName
import kr.pokeum.fragile_codegen.extension.isExtendedBy
import kr.pokeum.fragile_codegen.extension.isNullablePrimitive
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

class FieldBinding(val fieldElement: Element, val annotation: SerializedName) {

    // Class or Interface
    fun isDeclared() = fieldElement.asType().kind == TypeKind.DECLARED

    // Byte, Short, Int, Long, Float, Double, Boolean, Char
    fun isPrimitive() = fieldElement.asType().kind.isPrimitive
    fun isNullablePrimitive() = fieldElement.asType().isNullablePrimitive()

    fun getType(): TypeMirror = fieldElement.asType()

    fun getDeclaredType(): KClass<*>? {
        if (isDeclared()) {
            val typeMirror = fieldElement.asType()
            val declaredType = typeMirror as DeclaredType
            val typeElement = declaredType.asElement() as TypeElement
            return Class.forName(typeElement.qualifiedName.toString()).kotlin
        }
        return null
    }

    fun <T> isExtendedBy(`class`: Class<T>) = fieldElement.isExtendedBy(`class`)

    fun getKeyName() = annotation.value

    fun getFieldName() = fieldElement.simpleName.toString()
}