package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.norah.nooralhadith.R
import kotlinx.coroutines.delay

@Composable

fun SplashScreen(

    onFinish: () -> Unit

) {

    LaunchedEffect(Unit) {

        delay(3000) // ⏱️ 3 ثواني فقط

        onFinish()

    }

    Box(

        modifier = Modifier.Companion.fillMaxSize()

    ) {

        // صورة السبلاش (صورة واحدة فقط)

        Image(

            painter = painterResource(id = R.drawable.logo11),

            contentDescription = null,

            modifier = Modifier.Companion.fillMaxSize(),

            contentScale = ContentScale.Companion.Crop

        )

        // النص تحت الولدين والكتاب

        Column(

            modifier = Modifier.Companion

                .fillMaxSize()

                .padding(top = 570.dp), // ⬅️ نزلناه تحتهم

            horizontalAlignment = Alignment.Companion.CenterHorizontally

        ) {

            Text(

                text = "نور الحديث",

                fontSize = 50.sp,

                fontWeight = FontWeight.Companion.ExtraBold,

                color = Color(0xFF3A6EA5) // أزرق واضح

            )

            Spacer(modifier = Modifier.Companion.height(6.dp))

            Text(

                text = "أحاديث للأطفال بطريقة لطيفة",

                fontSize = 20.sp,

                fontWeight = FontWeight.Companion.Medium,

                color = Color(0xFF4F6D8A)

            )

        }

    }

}