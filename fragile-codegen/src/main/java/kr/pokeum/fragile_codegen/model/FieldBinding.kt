package kr.pokeum.fragile_codegen.model

import kr.pokeum.fragile.annotation.SerializedName
import kr.pokeum.fragile_codegen.extension.isExtendedBy
import kr.pokeum.fragile_codegen.extension.isNullablePrimitive
import javax.lang.model.element.Element
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

class FieldBinding(val fieldElement: Element, val annotation: SerializedName) {

    // Class or Interface
    fun isDeclared() = fieldElement.asType().kind == TypeKind.DECLARED

    // Byte, Short, Int, Long, Float, Double, Boolean, Char
    fun isPrimitive() = fieldElement.asType().kind.isPrimitive
    fun isNullablePrimitive() = fieldElement.asType().isNullablePrimitive()

    fun getType(): TypeMirror = fieldElement.asType()

    fun <T> isExtendedBy(`class`: Class<T>) = fieldElement.isExtendedBy(`class`)

    fun getKeyName() = annotation.value

    fun getFieldName() = fieldElement.simpleName.toString()
}