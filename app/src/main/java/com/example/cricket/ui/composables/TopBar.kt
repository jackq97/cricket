package com.example.cricket.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    TopAppBar {
        Spacer(Modifier.width(8.dp))

        Text(
            text = "",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

