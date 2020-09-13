package david.angulo.productsApp.modules.home.activity

import android.app.Application
import david.angulo.productsApp.application.App
import david.angulo.productsApp.modules.base.BaseViewModel

class HomeActivityViewModel (app: Application) : BaseViewModel(app) {

    init {
        (app as? App)?.component?.inject(this)
    }
}