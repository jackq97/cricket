package com.example.cricket.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioGroup(onClickOption : (String) -> Unit) {

    val options = listOf(
        "Upcoming",
        "Recent")

    var selectedOption by remember {
        mutableStateOf("Upcoming")
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .border(
                width = 4.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(34.dp)
            )
            .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
    ) {

        options.forEach { text ->

            val color = if (text == selectedOption) Color.Blue else Color.LightGray
            val font = if (text == selectedOption) FontWeight.ExtraBold else FontWeight.Bold

            Button(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(100)),
                onClick = {  onSelectionChange(text)
                         onClickOption(text) },
                colors = ButtonDefaults.buttonColors(backgroundColor = color)) {
                Text(text = text,
                    fontWeight = font)
            }

        }
    }
}

@Preview
@Composable
fun PreviewCustomRadioGroup(){
    CustomRadioGroup(onClickOption = {})
}