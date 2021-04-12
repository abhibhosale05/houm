package com.houm.android.houmweather.base

import com.houm.android.houmweather.intf.BasePresenter
import com.houm.android.houmweather.intf.BaseView
import java.lang.ref.WeakReference

abstract class AbstractBasePresenter<View : BaseView> : BasePresenter<View> {


    private var reference: WeakReference<View>? = null


    override fun getView(): View? {
        return reference?.get()
    }

    override fun detachView() {
        reference?.clear()
    }

    override fun attachView(view: View) {
        reference = WeakReference(view)
    }

    protected fun getContext() = getView()?.getContext()

    protected fun getActivity() = getView()?.getActivity()

}