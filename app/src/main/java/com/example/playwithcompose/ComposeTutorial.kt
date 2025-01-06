package com.example.playwithcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.playwithcompose.ui.theme.PlayWithComposeTheme

class ComposeTutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayWithComposeTheme{
                ComposeBasics()
            }
        }
    }
}

@Composable
fun ComposeBasics(modifier: Modifier = Modifier) {
  Column(modifier) {
      Image(
          painter = painterResource(R.drawable.bg_compose_background),
          contentDescription = null,
          modifier = Modifier.fillMaxWidth()
      )
      Text(
          text = stringResource(R.string.heading_compose_tutorial),
          fontSize = 24.sp,
          modifier = Modifier.padding(16.dp)
      )
      Text(
          text = stringResource(R.string.paragraph_1),
          fontSize = 24.sp,
          modifier = Modifier.padding(16.dp),
          textAlign = TextAlign.Justify
      )
      Text(
          text = stringResource(R.string.paragraph_2),
          fontSize = 24.sp,
          modifier = Modifier.padding(16.dp),
          textAlign = TextAlign.Justify
      )
  }
}

@Preview(showBackground = true)
@Composable
fun ComposeTutorialPreview() {
    PlayWithComposeTheme{
       ComposeBasics()
    }
}