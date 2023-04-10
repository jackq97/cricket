package com.example.cricket.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TopBar(text: String = "TEST", destinationsNavigator: DestinationsNavigator) {

    TopAppBar {

        IconButton(onClick = { destinationsNavigator.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back"
            )
        }

        Spacer(Modifier.width(10.dp))

        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp
        )
    }
}

