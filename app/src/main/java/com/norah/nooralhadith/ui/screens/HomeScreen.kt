package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.Image

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalLayoutDirection

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.LayoutDirection

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import com.norah.nooralhadith.R

@Composable

fun HomeScreen(

    onHadithListClick: () -> Unit,

    onQuizClick: () -> Unit

) {

    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

    val pastelBg = if (!isDark) {

        Brush.verticalGradient(

            colors = listOf(

                Color(0xFFEFE5FF),

                Color(0xFFFFE1F1),

                Color(0xFFE6F2FF),

                Color(0xFFFFF3C9)

            )

        )

    } else {

        Brush.verticalGradient(

            colors = listOf(

                Color(0xFF1B1620),

                Color(0xFF1A1F2A),

                Color(0xFF12151D),

                Color(0xFF1E1A14)

            )

        )

    }

    val titleColor = MaterialTheme.colorScheme.primary

    val subTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    val circleColor = MaterialTheme.colorScheme.primary

    val circleTextColor = MaterialTheme.colorScheme.onPrimary

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(pastelBg)

                .padding(horizontal = 16.dp)

                .statusBarsPadding(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(22.dp))

            Text(

                text = "أهلًا بكم بنور الحديث",

                fontSize = 22.sp,

                fontWeight = FontWeight.ExtraBold,

                color = titleColor,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Center

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "سنتعلّم مع بعض أحاديث كثيرة",

                fontSize = 14.sp,

                color = subTextColor,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Center

            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(

                modifier = Modifier

                    .fillMaxWidth()

                    .height(140.dp),

                colors = CardDefaults.cardColors(

                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.35f else 0.70f)

                ),

                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

            ) {

                Image(

                    painter = painterResource(id = R.drawable.banner1),

                    contentDescription = null,

                    modifier = Modifier.fillMaxSize(),

                    contentScale = ContentScale.Crop

                )

            }

            Spacer(modifier = Modifier.height(36.dp))

            Box(

                modifier = Modifier.fillMaxWidth(),

                contentAlignment = Alignment.Center

            ) {

                CircleAction(

                    text = "قائمة الأحاديث",

                    bg = circleColor,

                    textColor = circleTextColor,

                    size = 175.dp,

                    onClick = onHadithListClick

                )

                Image(

                    painter = painterResource(id = R.drawable.girl1),

                    contentDescription = null,

                    modifier = Modifier

                        .size(150.dp)

                        .align(Alignment.CenterStart)

                        .offset(x = 5.dp),

                    contentScale = ContentScale.Fit

                )

            }

            Spacer(modifier = Modifier.height(68.dp))

            Box(

                modifier = Modifier.fillMaxWidth(),

                contentAlignment = Alignment.Center

            ) {

                CircleAction(

                    text = "اختبر معلوماتي",

                    bg = circleColor,

                    textColor = circleTextColor,

                    size = 175.dp,

                    onClick = onQuizClick

                )

                Image(

                    painter = painterResource(id = R.drawable.boy1),

                    contentDescription = null,

                    modifier = Modifier

                        .size(150.dp)

                        .align(Alignment.CenterEnd)

                        .offset(x = (-6).dp),

                    contentScale = ContentScale.Fit

                )

            }

            Spacer(modifier = Modifier.height(24.dp))

        }

    }

}

@Composable

private fun CircleAction(

    text: String,

    bg: Color,

    textColor: Color,

    size: Dp,

    onClick: () -> Unit

) {

    Box(

        modifier = Modifier

            .size(size)

            .clip(CircleShape)

            .background(bg)

            .clickable { onClick() },

        contentAlignment = Alignment.Center

    ) {

        Text(

            text = text,

            fontSize = 16.sp,

            fontWeight = FontWeight.SemiBold,

            color = textColor,

            textAlign = TextAlign.Center

        )

    }

}
