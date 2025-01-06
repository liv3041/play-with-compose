package com.example.playwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playwithcompose.ui.theme.PlayWithComposeTheme

class TaskManager : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayWithComposeTheme {
                TasksCompleted()

            }
        }
    }
}

@Composable
fun TasksCompleted(modifier: Modifier = Modifier) {
   Column(
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center,
       modifier = Modifier.fillMaxSize()
   ) {
       Image(
           painter = painterResource(R.drawable.ic_task_completed),
           contentDescription = "Task Completed"
       )
      Text(
          text = stringResource(R.string.task_completed_text),
          modifier = Modifier.padding(
              top = 24.dp,
              bottom = 8.dp
          ),
          style = TextStyle(
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold
          )
      )
      Text(
          text = stringResource(R.string.appreciation_text),
          modifier = Modifier.padding(
              top = 8.dp,
              bottom = 8.dp
          ),
          style = TextStyle(
              fontSize = 16.sp,
              fontWeight = FontWeight.Normal
          ),
          textAlign = TextAlign.Center
      )

   }
}



@Preview(showBackground = true)
@Composable
fun TaskManagerPreview() {
    PlayWithComposeTheme{
     TasksCompleted()
    }
}