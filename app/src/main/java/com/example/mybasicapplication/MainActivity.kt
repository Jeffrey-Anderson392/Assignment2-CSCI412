package com.example.mybasicapplication
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybasicapplication.ui.theme.MyBasicApplicationTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.mybasicapplication.ui.theme.MyBasicApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        setContent {
            MyBasicApplicationTheme {
                ScaffoldMainActivity(requestPermissionLauncher)
            }
        }

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, handle action
                val intent = Intent("com.example.ACTION_OPEN_SECOND_ACTIVITY")
                startActivity(intent)
            } else {
                // Permission denied, handle accordingly (e.g., show a message)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldMainActivity(requestPermissionLauncher: ActivityResultLauncher<String>) {
    var presses by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                    Text(
                        textAlign = TextAlign.Center,
                        text = "Main Activity",

                        )
                },

            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = "Jeffrey Anderson \nStudent ID: 1420817",
                    fontSize = 50.sp,
                    lineHeight = 116.sp,
                    textAlign = TextAlign.Center,

                    )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                {
                    ImplicitButton(requestPermissionLauncher)
                    Text(
                        text = "     ",
                        lineHeight = 116.sp,
                        textAlign = TextAlign.Center,

                        )

                    ExplicitButton(requestPermissionLauncher)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                {
                    CameraButton()
                }
                @Composable
                fun Greeting(name: String, modifier: Modifier = Modifier) {
                    Text(
                        text = "Hello $name!",
                        modifier
                        = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun ImplicitButton(requestPermissionLauncher: ActivityResultLauncher<String>, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick =  {
            requestPermissionLauncher.launch("com.example.mybasicapplication.MSE412")

        }) {
            Text("Implicit Button")

        }
    }
}

@Composable
fun ExplicitButton(requestPermissionLauncher: ActivityResultLauncher<String>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick =  {
            requestPermissionLauncher.launch("com.example.mybasicapplication.MSE412")

        }) {
            Text("Explicit Button")

        }
    }
}


@Composable
                fun CameraButton(modifier: Modifier = Modifier) {
                    val context = LocalContext.current
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            val intent = Intent(context, ThirdActivity::class.java)
                            context.startActivity(intent)

                        }) {
                            Text("View Image Activity")

                        }
                    }
                }





