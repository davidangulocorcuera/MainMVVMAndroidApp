package david.angulo.productsApp.modules.utils


import android.app.Application
import android.content.Context


class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: MainApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        val context: Context =
            applicationContext()
    }


}