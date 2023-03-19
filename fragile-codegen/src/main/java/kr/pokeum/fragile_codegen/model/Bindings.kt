package kr.pokeum.fragile_codegen.model

import kr.pokeum.fragile.annotation.Deserializable
import kr.pokeum.fragile.annotation.SerializedName
import kr.pokeum.fragile_codegen.ProcessingEnvUtils
import kr.pokeum.fragile_codegen.extension.getEnclosedFieldElements
import kr.pokeum.fragile_codegen.extension.getPackage
import kr.pokeum.fragile_codegen.extension.isNotAccessible
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

class Bindings {

    private val packageBindingInfo = mutableMapOf<PackageElement, PackageBinding>()

    fun build(env: RoundEnvironment) {
        val classElements = env.getElementsAnnotatedWith(Deserializable::class.java)
        for (classElement in classElements) {
            if (classElement.isNotAccessible()) {
                ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                    "Class is not accessible, it cannot be private or static to bind")
            }
            val packageElement = classElement.getPackage()
            val packageBinding = packageBindingInfo.computeIfAbsent(packageElement) { PackageBinding(packageElement) }
            val classBinding = packageBinding.getClassBinding(classElement as TypeElement)
            val fieldElements = classElement.getEnclosedFieldElements()
            for (fieldElement in fieldElements) {
                val annotation = fieldElement.getAnnotation(SerializedName::class.java) ?: continue
                if (fieldElement.isNotAccessible()) {
                    ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                        "Field is not accessible, it cannot be private or static to bind",
                        fieldElement)
                }
                val fieldBinding = FieldBinding(fieldElement, annotation)
                classBinding.addFieldBinding(fieldBinding)
            }
        }
    }

    fun getPackageBindings(): Collection<PackageBinding> {
        return packageBindingInfo.values
    }

    companion object {
        private const val TAG = "Bindings"
    }
}