package cz.angelina.kotlingithub.architecture

import androidx.lifecycle.ViewModel
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
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
internal class PresentationLayerTest : ArchitectureTest() {
    @ArchTest
    val `presentation layer should contain only specified classes` =
        classes().that().resideInAPackage("..presentation..").and(areNotNested)
            .should().haveSimpleNameEndingWith("ViewModel")

    @ArchTest
    val `view model subclasses should have correct name` =
        classes().that().areAssignableTo(ViewModel::class.java)
            .should().haveSimpleNameEndingWith("ViewModel")

    @ArchTest
    val `view model classes should extend correct base class` =
        classes().that().haveSimpleNameEndingWith("ViewModel")
            .should().beAssignableTo(ViewModel::class.java)
            .orShould().beInterfaces()
}
