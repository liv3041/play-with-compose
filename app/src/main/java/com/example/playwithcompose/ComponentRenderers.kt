package com.example.playwithcompose

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.playwithcompose.models.Components


@Composable
    fun RenderButton(label: String) {
        Button(onClick = { /* Handle click */ }) {
            Text(text = label)
        }
    }

    @Composable
    fun RenderText(text: String) {
        Text(text = text)
    }

    @Composable
    fun RenderImage(url: String) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
    }


    @Composable
    fun RenderComponent(component: Components) {
        when (component.type) {
            "image" -> if (component.url != null) RenderImage(url = component.url)
            "button" -> if (component.label != null) RenderButton(label = component.label)
            "text" -> if (component.text != null) RenderText(text = component.text)

        }
    }


