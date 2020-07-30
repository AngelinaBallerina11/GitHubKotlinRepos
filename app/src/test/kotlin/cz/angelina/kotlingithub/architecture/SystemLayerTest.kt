package cz.angelina.kotlingithub.architecture

import android.app.Activity
import android.app.Application
import android.app.Service
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeGeneratedFiles
import cz.angelina.kotlingithub.architecture.ArchitectureTest.DoNotIncludeTests

@AnalyzeClasses(
    packages = [ArchitectureTest.PACKAGE_NAME],
    importOptions = [
        DoNotIncludeTests::class,
        DoNotIncludeGeneratedFiles::class
    ]
)
internal class SystemLayerTest : ArchitectureTest() {
    @ArchTest
    val `system layer should contain only specified classes` =
        classes().that().resideInAPackage("..system..").and(areNotNested)
            .should().haveSimpleNameEndingWith("Application")
            .orShould().haveSimpleNameEndingWith("Activity")
            .orShould().haveSimpleNameEndingWith("ActivityDelegate")
            .orShould().haveSimpleNameEndingWith("Fragment")
            .orShould().haveSimpleNameEndingWith("View")
            .orShould().haveSimpleNameEndingWith("Layout")
            .orShould().haveSimpleNameEndingWith("Service")
            .orShould().haveSimpleNameEndingWith("Adapter")
            .orShould().haveSimpleNameEndingWith("ExtensionsKt")

    @ArchTest
    val `application subclasses should have correct name` =
        classes().that().areAssignableTo(Application::class.java)
            .should().haveSimpleNameEndingWith("Application")

    @ArchTest
    val `activity subclasses should have correct name` =
        classes().that().areAssignableTo(Activity::class.java)
            .should().haveSimpleNameEndingWith("Activity")

    @ArchTest
    val `fragment subclasses should have correct name` =
        classes().that().areAssignableTo(Fragment::class.java)
            .should().haveSimpleNameEndingWith("Fragment")

    @ArchTest
    val `view subclasses should have correct name` =
        classes().that().areAssignableTo(View::class.java)
            .should().haveSimpleNameEndingWith("View")
            .orShould().haveSimpleNameEndingWith("Layout")

    @ArchTest
    val `service subclasses should have correct name` =
        classes().that().areAssignableTo(Service::class.java)
            .should().haveSimpleNameEndingWith("Service")

    @ArchTest
    val `adapter subclasses should have correct name` =
        classes().that().areAssignableTo(RecyclerView.Adapter::class.java)
            .should().haveSimpleNameEndingWith("Adapter")
}
