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
import androidx.navigation.NavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.vk.directop.ratemarketapp.presentation.company_listings.NavGraphs
import com.vk.directop.ratemarketapp.ui.theme.RateMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
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