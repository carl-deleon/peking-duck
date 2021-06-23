package com.scrrrt.kompoze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.scrrrt.kompoze.data.DataModule
import com.scrrrt.kompoze.data.dto.request.SubmitFormRequest
import com.scrrrt.kompoze.data.remote.apiadapter.NetworkResponse
import com.scrrrt.kompoze.ui.theme.KompozeTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val apiService by lazy { DataModule.getApiService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KompozeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            val request = SubmitFormRequest(
                name = "Carl Justine De leon",
                age = 29
            )

            when (val data = apiService.getCats()) {
                is NetworkResponse.ApiError -> Timber.e(data.body.message)
                is NetworkResponse.NetworkError -> Timber.e(data.error)
                is NetworkResponse.Success -> Timber.d(data.body.toString())
                is NetworkResponse.UnknownError -> Timber.e(data.error)
                is NetworkResponse.OtpRequired -> Timber.d(data.otpResponse.toString())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KompozeTheme {
        Greeting("Android")
    }
}