package com.example.android_studio_test_exercice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_studio_test_exercice.ui.theme.AndroidstudiotestexerciceTheme
import com.example.android_studio_test_exercice.view.*
import com.example.android_studio_test_exercice.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myViewModel by viewModels<MainViewModel>()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidstudiotestexerciceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(myViewModel = myViewModel, modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}