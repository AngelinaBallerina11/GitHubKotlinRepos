package cz.angelina.kotlingithub.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeGeneratedFiles
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeTests
import cz.angelina.kotlingithub.domain.SuspendUseCase

@AnalyzeClasses(
    packages = [ArchitectureTest.PACKAGE_NAME],
    importOptions = [
        DoNotIncludeTests::class,
        DoNotIncludeGeneratedFiles::class
    ]
)
internal class DomainLayerTest : ArchitectureTest() {
    @ArchTest
    val `domain layer should contain only specified classes` =
        classes().that().resideInAPackage("..domain")
            .should().haveSimpleNameEndingWith("Repository").andShould().beInterfaces()
            .orShould().haveSimpleNameEndingWith("UseCaseKt")
            .orShould().haveSimpleNameEndingWith("UseCase")

    @ArchTest
    val `suspend use case subclasses should have correct name` =
        classes().that().areAssignableTo(SuspendUseCase::class.java)
            .should().haveSimpleNameEndingWith("UseCase")
}
