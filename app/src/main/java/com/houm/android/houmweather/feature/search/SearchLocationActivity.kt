package com.houm.android.houmweather.feature.search

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.houm.android.houmweather.R
import com.houm.android.houmweather.base.BaseActivity
import com.houm.android.houmweather.feature.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search_location.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchLocationActivity : BaseActivity(), SearchLocation.View {

    @Inject
    lateinit var presenter: SearchLocationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)
        presenter.attachView(this)
    }

    override fun onInitialiseListener() {
        submit_button.setOnClickListener {
            presenter.isValidInput(
                latitude_edittext.text.toString().trim().toDoubleOrNull(),
                longitude_edittext.text.toString().trim().toDoubleOrNull()
            )
        }
    }

    override fun onValidInput() {
        val intent = Intent()
        intent.putExtra(
            DashboardActivity.LATITUDE_KEY,
            latitude_edittext.text.toString().trim().toDouble()
        )
        intent.putExtra(
            DashboardActivity.LONGITUDE_KEY,
            longitude_edittext.text.toString().trim().toDouble()
        )
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onInvalidInput() {
        Toast.makeText(baseContext,getString(R.string.str_search_location_empty),Toast.LENGTH_LONG).show()
    }
}