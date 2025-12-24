package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.Image

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalLayoutDirection

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.LayoutDirection

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import com.norah.nooralhadith.R

import com.norah.nooralhadith.data.local.HadithMeta

import com.norah.nooralhadith.data.local.hadithMetaList

@Composable

fun HadithListScreen(

    onBackClick: () -> Unit,

    onHadithClick: (Int) -> Unit,

    hadithList: List<HadithMeta> = hadithMetaList

) {

    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

    val bg = if (!isDark) {

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

    val cardBg = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.65f else 0.85f)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(bg)

                .padding(horizontal = 16.dp)

                .statusBarsPadding()

        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Box(

                modifier = Modifier

                    .fillMaxWidth()

                    .padding(top = 4.dp),

                contentAlignment = Alignment.Center

            ) {

                IconButton(

                    onClick = onBackClick,

                    modifier = Modifier

                        .align(Alignment.CenterStart)

                        .size(48.dp)

                ) {

                    Icon(

                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,

                        contentDescription = "Back",

                        tint = subTextColor

                    )

                }

                Text(

                    text = "قائمة الأحاديث",

                    fontSize = 20.sp,

                    fontWeight = FontWeight.SemiBold,

                    color = titleColor,

                    textAlign = TextAlign.Center,

                    modifier = Modifier.fillMaxWidth()

                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(

                modifier = Modifier

                    .fillMaxWidth()

                    .height(140.dp),

                colors = CardDefaults.cardColors(containerColor = cardBg),

                elevation = CardDefaults.cardElevation(0.dp)

            ) {

                Image(

                    painter = painterResource(id = R.drawable.home_banner),

                    contentDescription = null,

                    modifier = Modifier.fillMaxSize(),

                    contentScale = ContentScale.Crop

                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(

                modifier = Modifier.fillMaxSize(),

                contentPadding = PaddingValues(bottom = 18.dp)

            ) {

                items(hadithList) { item ->

                    HadithRow(

                        title = item.title,

                        narrator = "الراوي: ${item.narrator}",

                        onClick = { onHadithClick(item.id) }

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                }

            }

        }

    }

}

@Composable

private fun HadithRow(

    title: String,

    narrator: String,

    onClick: () -> Unit

) {

    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

    val cardBg = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.70f else 0.88f)

    val titleColor = MaterialTheme.colorScheme.onSurface

    val narratorColor = MaterialTheme.colorScheme.onSurfaceVariant

    val arrowColor = MaterialTheme.colorScheme.primary

    Card(

        modifier = Modifier

            .fillMaxWidth()

            .clickable { onClick() },

        colors = CardDefaults.cardColors(containerColor = cardBg),

        elevation = CardDefaults.cardElevation(0.dp)

    ) {

        Row(

            modifier = Modifier

                .fillMaxWidth()

                .padding(14.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(modifier = Modifier.weight(1f)) {

                Text(

                    text = title,

                    fontSize = 16.sp,

                    fontWeight = FontWeight.SemiBold,

                    color = titleColor,

                    textAlign = TextAlign.Right,

                    modifier = Modifier.fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = narrator,

                    fontSize = 12.sp,

                    color = narratorColor,

                    textAlign = TextAlign.Right,

                    modifier = Modifier.fillMaxWidth()

                )

            }

            Text(

                text = "›",

                fontSize = 22.sp,

                color = arrowColor,

                modifier = Modifier.padding(start = 10.dp)

            )

        }

        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))

    }

}
