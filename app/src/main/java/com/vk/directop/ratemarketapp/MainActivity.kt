package com.vk.directop.ratemarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vk.directop.ratemarketapp.ui.theme.RateMarketAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMarketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name! PV Rate Market")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RateMarketAppTheme {
        Greeting("Android")
    }
}


//alphavantage.co
//echo "# RateMarketApp" >> README.md
//git init
//git add README.md
//git commit -m "first commit"
//git remote add origin https://github.com/pavelfire/RateMarketApp.git
//git branch -M main
//git push -u origin main