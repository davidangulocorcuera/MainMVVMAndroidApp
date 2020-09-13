package david.angulo.productsApp.modules.home.activity

import android.os.Bundle
import david.angulo.productsApp.R
import david.angulo.productsApp.databinding.ActivityHomeBinding
import david.angulo.productsApp.modules.base.BaseActivity
import david.angulo.productsApp.modules.home.HomeFragment

class HomeActivity : BaseActivity<HomeActivityViewModel, ActivityHomeBinding>(
    HomeActivityViewModel::class.java
) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(viewModel: HomeActivityViewModel) {
        binding.homeActivityViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.addFragment(HomeFragment(), R.id.fragmentContainerHome)
    }
}
