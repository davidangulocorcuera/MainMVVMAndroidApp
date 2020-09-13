package david.angulo.productsApp.modules.home


import android.view.View
import david.angulo.productsApp.databinding.FragmentHomeBinding
import david.angulo.productsApp.R
import david.angulo.productsApp.modules.base.BaseFragment


class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>(
    HomeFragmentViewModel::class.java
) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewCreated(view: View?) {

    }
}
