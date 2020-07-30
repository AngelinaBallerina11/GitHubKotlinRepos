package cz.angelina.kotlingithub.architecture

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.core.importer.Location
import java.util.regex.Pattern

internal abstract class ArchitectureTest {

    internal class DoNotIncludeGeneratedFiles : ImportOption {
        companion object {
            private val pattern =
                Pattern.compile(".*/BuildConfig\\.class|.*/R(\\\$.*)?\\.class|.*_.*|.*Binding\\.class")
        }

        override fun includes(location: Location) = !location.matches(pattern)
    }

    internal class DoNotIncludeTests : ImportOption {
        override fun includes(location: Location) = !location.contains("Test")
    }

    protected val areNotNested = object : DescribedPredicate<JavaClass>("are not nested") {
        override fun apply(input: JavaClass) = !input.name.contains('$')
    }

    companion object {
        const val PACKAGE_NAME = "cz.angelina.kotlingithub"
    }
}
