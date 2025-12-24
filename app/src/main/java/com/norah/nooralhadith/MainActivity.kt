package com.norah.nooralhadith

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.norah.nooralhadith.ui.navigation.AppNavGraph

import com.norah.nooralhadith.ui.theme.NoorAlHadithTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            NoorAlHadithTheme {

                val navController = rememberNavController()

                AppNavGraph(navController)

            }

        }

    }

}
