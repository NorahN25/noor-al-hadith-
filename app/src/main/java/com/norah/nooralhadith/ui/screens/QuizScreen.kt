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
    val bg = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFEFE5FF),
            Color(0xFFFFE1F1),
            Color(0xFFE6F2FF),
            Color(0xFFFFF3C9)
        )
    )

    val titleColor = Color(0xFFB23A78)
    val subTextColor = Color(0xFF6B5B72)
    val cardBg = Color.White.copy(alpha = 0.70f)

    // ✅ الرواة الموجودين عندك محليًا (نجيبهم من الميتا)
    val allNarrators = remember {
        hadithMetaList.map { it.narrator.trim() }.distinct()
    }

    // ✅ بنك الأسئلة من local + خيارين (صح + راوي ثاني)
    fun buildQuestionsPool(): List<QuizQuestion> {
        return hadithMetaList.map { meta ->
            val correct = meta.narrator.trim()

            // راوي ثاني مختلف (أي واحد)
            val wrong = allNarrators.filter { it != correct }.randomOrNull() ?: correct

            val options = listOf(correct, wrong).shuffled()

            QuizQuestion(
                id = meta.id,
                matn = meta.simpleMatn,          // ✅ من local
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

            // ✅ الجملة الجديدة
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
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.65f)),
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
                        containerColor = if (feedbackIsCorrect) Color(0xFFE9FFF1) else Color(0xFFFFEFEF)
                    ),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Text(
                        text = feedbackText!!,
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = if (feedbackIsCorrect) Color(0xFF1E7A3A) else Color(0xFFA11E1E)
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB23A78))
                ) {
                    Text(
                        text = "اختبار جديد",
                        color = Color.White,
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
                color = Color(0xFF2E5AAC),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // ✅ خيارين: واحد صح + واحد راوي ثاني
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
                    color = Color(0xFF1E7A3A),
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
            containerColor = if (enabled) Color.White else Color(0xFFF3F3F3)
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, Color(0xFFE7D7F2))
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6B5B72),
                textAlign = TextAlign.Center
            )
        }
    }
}