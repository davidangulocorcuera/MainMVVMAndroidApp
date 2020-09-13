package david.angulo.productsApp.modules.home

import android.app.Application
import david.angulo.productsApp.application.App
import david.angulo.productsApp.modules.base.BaseViewModel

class HomeFragmentViewModel(var app: Application) : BaseViewModel(app){
    init {
        (app as? App)?.component?.inject(this)
    }
}