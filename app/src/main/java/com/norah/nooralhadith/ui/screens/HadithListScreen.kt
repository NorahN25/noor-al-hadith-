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

    val pastelBg = Brush.verticalGradient(

        colors = listOf(

            Color(0xFFEFE5FF),

            Color(0xFFFFE1F1),

            Color(0xFFE6F2FF),

            Color(0xFFFFF3C9)

        )

    )

    val subTextColor = Color(0xFF6B5B72)

    val cardBg = Color.White.copy(alpha = 0.65f)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(pastelBg)

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

                    color = subTextColor,

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

    val cardBg = Color.White.copy(alpha = 0.70f)

    val titleColor = Color(0xFF3E3A48)

    val narratorColor = Color(0xFF7A7288)

    val arrowColor = Color(0xFF7A6FA0)

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

        Divider(color = Color(0xFFF2EDF8))

    }

}
