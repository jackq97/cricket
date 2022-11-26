package com.example.cricket.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cricket.R

// Set of Material typography styles to start with

val Tight  = FontFamily(
    Font(R.font.intertight_black),
    Font(R.font.intertight_blackitalic),
    Font(R.font.intertight_bold),
    Font(R.font.intertight_extrabold),
    Font(R.font.intertight_bolditalic),
    Font(R.font.intertight_extrabolditalic),
    Font(R.font.intertight_extralight),
    Font(R.font.intertight_extralightitalic),
    Font(R.font.intertight_italic),
    Font(R.font.intertight_light),
    Font(R.font.intertight_lightitalic),
    Font(R.font.intertight_medium),
    Font(R.font.intertight_mediumitalic),
    Font(R.font.intertight_regular),
    Font(R.font.intertight_semibold),
    Font(R.font.intertight_black),
    Font(R.font.intertight_thinitalic),
    Font(R.font.intertight_thin),

)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)