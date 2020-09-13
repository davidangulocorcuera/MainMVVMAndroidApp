package david.angulo.productsApp.modules.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import david.angulo.productsApp.R
import david.angulo.productsApp.modules.utils.setVisible


abstract class BaseDialogFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    DialogFragment() {
    lateinit var viewModel: VM
    open lateinit var mBinding: DB
    val LOG_TAG = this::class.java.simpleName


    fun init() {
        mBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutRes(), null, false)
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun viewCreated(view: View?)


    private fun getViewM(): VM = ViewModelProviders.of(this).get(mViewModelClass)
    open fun onInject() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        init()
        viewModel = getViewM()
        return AlertDialog.Builder(context!!)
            .setView(mBinding.root)
            .create()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        viewCreated(view)
    }

    open fun refresh() {}

    fun showProgress(show: Boolean) {
        val progress = dialog?.findViewById<ProgressBar>(R.id.progress)
        progress?.setVisible(show)
    }


    private val baseActivity: BaseActivity<*, *>?
        get() {
            return activity as? BaseActivity<*, *>
        }

}