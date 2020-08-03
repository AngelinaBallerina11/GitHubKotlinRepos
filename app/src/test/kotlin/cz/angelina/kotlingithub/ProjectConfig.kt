package cz.angelina.kotlingithub

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

object ProjectConfig : AbstractProjectConfig() {
    override val isolationMode: IsolationMode?
        get() = IsolationMode.InstancePerTest
}
