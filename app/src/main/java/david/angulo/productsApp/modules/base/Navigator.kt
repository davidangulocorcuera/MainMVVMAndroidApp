package david.angulo.productsApp.modules.base

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment


class Navigator(private val activity: BaseActivity<*, *>) {

    private val fragmentManager = activity.supportFragmentManager

    fun navigate(
        fragmentToGo: BaseFragment<*, *>,
        addBackStack: Boolean,
        tag: String,
        arguments: Bundle?,
        container: Int
    ) {

        val transaction = fragmentManager.beginTransaction()

        transaction.replace(
            container,
            fragmentToGo,
            tag
        )

        if (addBackStack) transaction.addToBackStack(tag)

        if (arguments != null) fragmentToGo.arguments = arguments

        transaction.commit()
    }

    fun addFragment(fragment: Fragment, container: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            container,
            fragment
        )
        transaction.commit()
    }

    fun removeFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(
            fragment
        )
        transaction.commit()
    }

    fun navigateToActivity(intent: Intent, bundle: Bundle){
        activity.finish()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity.applicationContext,intent,bundle)
    }


}