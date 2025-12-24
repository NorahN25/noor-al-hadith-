package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    val pastelBg = Brush.Companion.verticalGradient(

        colors = listOf(

            Color(0xFFEFE5FF),

            Color(0xFFFFE1F1),

            Color(0xFFE6F2FF),

            Color(0xFFFFF3C9)

        )

    )

    val titleColor = Color(0xFFB03A6A)

    val subTextColor = Color(0xFF6B5B72)

    val circleColor = Color(0xFFB03A6A)

    val circleTextColor = Color.Companion.White

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(

            modifier = Modifier.Companion

                .fillMaxSize()

                .background(pastelBg)

                .padding(horizontal = 16.dp)

                .statusBarsPadding(),

            horizontalAlignment = Alignment.Companion.CenterHorizontally

        ) {

            // ✅ نزلنا العنوان شوي لتحت

            Spacer(modifier = Modifier.Companion.height(22.dp))

            Text(

                text = "أهلًا بكم بنور الحديث",

                fontSize = 22.sp,

                fontWeight = FontWeight.Companion.ExtraBold,

                color = titleColor,

                modifier = Modifier.Companion.fillMaxWidth(),

                textAlign = TextAlign.Companion.Center

            )

            Spacer(modifier = Modifier.Companion.height(6.dp))

            Text(

                text = "سنتعلّم مع بعض أحاديث كثيرة",

                fontSize = 14.sp,

                color = subTextColor,

                modifier = Modifier.Companion.fillMaxWidth(),

                textAlign = TextAlign.Companion.Center

            )

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // ✅ (اختياري) إذا تبين تحذفينه لاحقًا

            Card(

                modifier = Modifier.Companion

                    .fillMaxWidth()

                    .height(140.dp),

                colors = CardDefaults.cardColors(containerColor = Color.Companion.White.copy(alpha = 0.35f)),

                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

            ) {

                Image(

                    painter = painterResource(id = R.drawable.banner1),

                    contentDescription = null,

                    modifier = Modifier.Companion.fillMaxSize(),

                    contentScale = ContentScale.Companion.Crop

                )

            }

            // ✅ الدائرة الأولى تنزل زيادة

            Spacer(modifier = Modifier.Companion.height(36.dp))

            // ========= الدائرة الأولى + (عكسنا اتجاه الولد) =========

            Box(

                modifier = Modifier.Companion.fillMaxWidth(),

                contentAlignment = Alignment.Companion.Center

            ) {

                CircleAction(

                    text = "قائمة الأحاديث",

                    bg = circleColor,

                    textColor = circleTextColor,

                    size = 175.dp, // ✅ كبرنا الدائرة شوي

                    onClick = onHadithListClick

                )

                // ✅ الولد صار في الجهة الثانية (يسار الشاشة) وكبرناه

                Image(

                    painter = painterResource(id = R.drawable.girl1),

                    contentDescription = null,

                    modifier = Modifier.Companion

                        .size(150.dp)

                        .align(Alignment.Companion.CenterStart)

                        .offset(x = 5.dp),

                    contentScale = ContentScale.Companion.Fit

                )

            }

            // ✅ مسافة أكبر بين الدائرتين

            Spacer(modifier = Modifier.Companion.height(68.dp))

            // ========= الدائرة الثانية + (عكسنا اتجاه البنت) =========

            Box(

                modifier = Modifier.Companion.fillMaxWidth(),

                contentAlignment = Alignment.Companion.Center

            ) {

                CircleAction(

                    text = "اختبر معلوماتي",

                    bg = circleColor,

                    textColor = circleTextColor,

                    size = 175.dp, // ✅ كبرنا الدائرة شوي

                    onClick = onQuizClick

                )

                // ✅ البنت صارت في الجهة الثانية (يمين الشاشة) وكبرناها

                Image(

                    painter = painterResource(id = R.drawable.boy1),

                    contentDescription = null,

                    modifier = Modifier.Companion

                        .size(150.dp)

                        .align(Alignment.Companion.CenterEnd)

                        .offset(x = (-6).dp),

                    contentScale = ContentScale.Companion.Fit

                )

            }

            Spacer(modifier = Modifier.Companion.height(24.dp))

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

        modifier = Modifier.Companion

            .size(size)

            .clip(CircleShape)

            .background(bg)

            .clickable { onClick() },

        contentAlignment = Alignment.Companion.Center

    ) {

        Text(

            text = text,

            fontSize = 16.sp,

            fontWeight = FontWeight.Companion.SemiBold,

            color = textColor,

            textAlign = TextAlign.Companion.Center

        )

    }

}