package cz.angelina.kotlingithub.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import cz.angelina.kotlingithub.architecture.ArchitectureTest.Companion.PACKAGE_NAME
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeGeneratedFiles
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeTests

@AnalyzeClasses(
    packages = [PACKAGE_NAME],
    importOptions = [
        DoNotIncludeTests::class,
        DoNotIncludeGeneratedFiles::class
    ]
)
internal class LayersTest : ArchitectureTest() {
    @ArchTest
    val `all classes should reside in one of the specified packages` =
        classes().should().resideInAnyPackage(
            "..di",
            "..system",
            "..data",
            "..presentation",
            "..domain",
            "..infrastructure"
        )

    @ArchTest
    val `layers should have correct dependencies` =
        layeredArchitecture()
            .optionalLayer("di").definedBy("..di")
            .optionalLayer("system").definedBy("..system")
            .optionalLayer("presentation").definedBy("..presentation")
            .optionalLayer("domain").definedBy("..domain")
            .optionalLayer("data").definedBy("..data")
            .optionalLayer("infrastructure").definedBy("..infrastructure")

            .whereLayer("di").mayOnlyBeAccessedByLayers("system")
            .whereLayer("system").mayOnlyBeAccessedByLayers("di")
            .whereLayer("presentation").mayOnlyBeAccessedByLayers("di", "system")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("di", "system", "data", "presentation", "infrastructure")
            .whereLayer("data").mayOnlyBeAccessedByLayers("di", "domain")
            .whereLayer("infrastructure").mayOnlyBeAccessedByLayers("di", "data", "domain")

    @ArchTest
    val `classes in lower layers should not depend on android imports` =
        noClasses().that().resideInAnyPackage("..domain..", "..infrastructure..")
            .should().dependOnClassesThat().resideInAnyPackage("android..", "androidx..", "com.google.android..", "com.google.firebase..")

    @ArchTest
    val `presentation classes should not depend on android imports except androidx lifecycle` =
        classes().that().resideInAPackage("..presentation..")
            .should().dependOnClassesThat().resideOutsideOfPackages(
                "android..",
                "com.google.android..",
                "com.google.firebase..",
                "androidx.activity..",
                "androidx.annotation..",
                "androidx.appcompat..",
                "androidx.arch..",
                "androidx.cardview..",
                "androidx.collection..",
                "androidx.constraintlayout..",
                "androidx.coordinatorlayout..",
                "androidx.core..",
                "androidx.cursoradapter..",
                "androidx.customview..",
                "androidx.databinding..",
                "androidx.drawerlayout..",
                "androidx.fragment..",
                "androidx.interpolator..",
                "androidx.loader..",
                "androidx.navigation..",
                "androidx.recyclerview..",
                "androidx.savedstate..",
                "androidx.transition..",
                "androidx.vectordrawable..",
                "androidx.versionedparcelable..",
                "androidx.viewbinding..",
                "androidx.viewpager..",
                "androidx.viewpager2.."
            )
}
