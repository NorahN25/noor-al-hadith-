package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft

import androidx.compose.material3.*

import androidx.compose.runtime.*

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

import com.norah.nooralhadith.data.local.hadithMetaList

data class QuizQuestion(

    val id: Int,

    val matn: String,

    val correctNarrator: String,

    val options: List<String>

)

@Composable

fun QuizScreen(

    onBackClick: () -> Unit,

    onCorrectAnswer: () -> Unit = {},

    onWrongAnswer: () -> Unit = {}

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

    val cardBg = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.70f else 0.88f)

    val allNarrators = remember {

        hadithMetaList.map { it.narrator.trim() }.distinct()

    }

    fun buildQuestionsPool(): List<QuizQuestion> {

        return hadithMetaList.map { meta ->

            val correct = meta.narrator.trim()

            val wrong = allNarrators.filter { it != correct }.randomOrNull() ?: correct

            val options = listOf(correct, wrong).shuffled()

            QuizQuestion(

                id = meta.id,

                matn = meta.simpleMatn,

                correctNarrator = correct,

                options = options

            )

        }

    }

    var questionsPool by remember { mutableStateOf(buildQuestionsPool()) }

    var selectedQuestions by remember { mutableStateOf(questionsPool.shuffled().take(2)) }

    var answeredState by remember { mutableStateOf<Map<Int, Boolean>>(emptyMap()) }

    var feedbackText by remember { mutableStateOf<String?>(null) }

    var feedbackIsCorrect by remember { mutableStateOf(false) }

    fun restartQuiz() {

        questionsPool = buildQuestionsPool()

        selectedQuestions = questionsPool.shuffled().take(2)

        answeredState = emptyMap()

        feedbackText = null

        feedbackIsCorrect = false

    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(bg)

                .statusBarsPadding()

                .padding(horizontal = 16.dp)

                .verticalScroll(rememberScrollState())

        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                IconButton(

                    onClick = onBackClick,

                    modifier = Modifier.align(Alignment.CenterStart).size(48.dp)

                ) {

                    Icon(

                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,

                        contentDescription = "Back",

                        tint = subTextColor

                    )

                }

                Text(

                    text = "اختبر معلوماتي",

                    fontSize = 20.sp,

                    fontWeight = FontWeight.ExtraBold,

                    color = titleColor,

                    textAlign = TextAlign.Center,

                    modifier = Modifier.fillMaxWidth()

                )

            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "هيا معنا لنختبر",

                fontSize = 16.sp,

                fontWeight = FontWeight.Bold,

                color = subTextColor,

                textAlign = TextAlign.Center,

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = "جرّب ذاكرتك: من هو الراوي الصحيح لكل حديث؟",

                fontSize = 13.sp,

                color = subTextColor,

                textAlign = TextAlign.Center,

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(

                modifier = Modifier.fillMaxWidth().height(140.dp),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(

                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.65f else 0.85f)

                ),

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

            if (feedbackText != null) {

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp),

                    colors = CardDefaults.cardColors(

                        containerColor = if (feedbackIsCorrect)

                            MaterialTheme.colorScheme.tertiaryContainer

                        else

                            MaterialTheme.colorScheme.errorContainer

                    ),

                    elevation = CardDefaults.cardElevation(0.dp)

                ) {

                    Text(

                        text = feedbackText!!,

                        modifier = Modifier.fillMaxWidth().padding(12.dp),

                        textAlign = TextAlign.Center,

                        fontWeight = FontWeight.Bold,

                        color = if (feedbackIsCorrect)

                            MaterialTheme.colorScheme.onTertiaryContainer

                        else

                            MaterialTheme.colorScheme.onErrorContainer

                    )

                }

                Spacer(modifier = Modifier.height(12.dp))

            }

            selectedQuestions.forEachIndexed { index, q ->

                QuestionCard(

                    number = index + 1,

                    question = q,

                    isAnsweredCorrect = answeredState[q.id] == true,

                    cardBg = cardBg,

                    titleColor = titleColor,

                    subTextColor = subTextColor,

                    onChoose = { chosen ->

                        val correct = (chosen == q.correctNarrator)

                        if (correct) {

                            answeredState = answeredState + (q.id to true)

                            feedbackText = "أحسنت! 👏"

                            feedbackIsCorrect = true

                            onCorrectAnswer()

                        } else {

                            feedbackText = "أوو.. حاول مرة أخرى 🙂"

                            feedbackIsCorrect = false

                            onWrongAnswer()

                        }

                    }

                )

                Spacer(modifier = Modifier.height(14.dp))

            }

            val allAnsweredCorrect = selectedQuestions.isNotEmpty() &&

                    selectedQuestions.all { answeredState[it.id] == true }

            if (allAnsweredCorrect) {

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = { restartQuiz() },

                    modifier = Modifier.fillMaxWidth().height(52.dp),

                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)

                ) {

                    Text(

                        text = "اختبار جديد",

                        color = MaterialTheme.colorScheme.onPrimary,

                        fontSize = 16.sp,

                        fontWeight = FontWeight.Bold

                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

        }

    }

}

@Composable

private fun QuestionCard(

    number: Int,

    question: QuizQuestion,

    isAnsweredCorrect: Boolean,

    cardBg: Color,

    titleColor: Color,

    subTextColor: Color,

    onChoose: (String) -> Unit

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(containerColor = cardBg),

        elevation = CardDefaults.cardElevation(0.dp)

    ) {

        Column(modifier = Modifier.padding(14.dp)) {

            Text(

                text = "سؤال $number",

                fontSize = 14.sp,

                fontWeight = FontWeight.Bold,

                color = titleColor,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Right

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "من راوي هذا الحديث؟",

                fontSize = 13.sp,

                fontWeight = FontWeight.SemiBold,

                color = subTextColor,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Right

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = question.matn,

                fontSize = 15.sp,

                fontWeight = FontWeight.Medium,

                color = MaterialTheme.colorScheme.onSurface,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Right

            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                OptionButton(

                    text = question.options[0],

                    enabled = !isAnsweredCorrect,

                    onClick = { onChoose(question.options[0]) },

                    modifier = Modifier.weight(1f)

                )

                OptionButton(

                    text = question.options[1],

                    enabled = !isAnsweredCorrect,

                    onClick = { onChoose(question.options[1]) },

                    modifier = Modifier.weight(1f)

                )

            }

            if (isAnsweredCorrect) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(

                    text = "تمت الإجابة ✅",

                    fontSize = 12.sp,

                    color = MaterialTheme.colorScheme.tertiary,

                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.fillMaxWidth(),

                    textAlign = TextAlign.Center

                )

            }

        }

    }

}

@Composable

private fun OptionButton(

    text: String,

    enabled: Boolean,

    onClick: () -> Unit,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier

            .height(44.dp)

            .clickable(enabled = enabled) { onClick() },

        shape = RoundedCornerShape(14.dp),

        colors = CardDefaults.cardColors(

            containerColor = if (enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant

        ),

        elevation = CardDefaults.cardElevation(0.dp),

        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)

    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Text(

                text = text,

                fontSize = 13.sp,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.onSurfaceVariant,

                textAlign = TextAlign.Center

            )

        }

    }

}

