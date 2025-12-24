package com.norah.nooralhadith.ui.screens

import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image

import androidx.compose.foundation.background

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

import androidx.compose.ui.draw.clip

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

import androidx.lifecycle.viewmodel.compose.viewModel

import com.norah.nooralhadith.data.local.NarratorInfo

import com.norah.nooralhadith.data.local.getHadithMetaById

import com.norah.nooralhadith.data.local.narratorsInfo

import com.norah.nooralhadith.di.ServiceLocator

import com.norah.nooralhadith.ui.state.UiState

import com.norah.nooralhadith.ui.viewmodel.HadithDetailsViewModel

import com.norah.nooralhadith.ui.viewmodel.HadithDetailsViewModelFactory

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun HadithDetailsScreen(

    id: Int,

    onBackClick: () -> Unit

) {

    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

    // ✅ نفس فكرتك: Gradient للفاتح، وGradient غامق للدارك

    val bg = remember(isDark) {

        if (!isDark) {

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFFEFE5FF),

                    Color(0xFFFFE1F1),

                    Color(0xFFE6F2FF),

                    Color(0xFFFFF3C9)

                )

            )

        } else {

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFF1B1620),

                    Color(0xFF1A1F2A),

                    Color(0xFF12151D),

                    Color(0xFF1E1A14)

                )

            )

        }

    }

    // ✅ ألوان من الثيم بدل ثوابت

    val headings = MaterialTheme.colorScheme.primary

    val contentColor = MaterialTheme.colorScheme.secondary

    val subtleText = MaterialTheme.colorScheme.onSurfaceVariant

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val scope = rememberCoroutineScope()

    var showSheet by remember { mutableStateOf(false) }

    var selectedNarratorInfo by remember { mutableStateOf<NarratorInfo?>(null) }

    // ✅ meta محلي

    val meta = getHadithMetaById(id)

    // ✅ ViewModel مع Factory

    val vm: HadithDetailsViewModel = viewModel(

        factory = HadithDetailsViewModelFactory(ServiceLocator.hadithRepository)

    )

    val state by vm.state.collectAsState()

    LaunchedEffect(id) {

        if (meta != null) vm.load(id)

    }

    val apiMatn = when (state) {

        is UiState.Idle -> "ابدئي بتحميل الحديث..."

        is UiState.Loading -> "جاري جلب نص الحديث..."

        is UiState.Success -> (state as UiState.Success<String>).data

        is UiState.Error -> "تعذّر جلب نص الحديث: ${(state as UiState.Error).message}"

        else -> "جاري تجهيز النص..."

    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        if (showSheet && selectedNarratorInfo != null) {

            ModalBottomSheet(

                onDismissRequest = { showSheet = false },

                sheetState = sheetState,

                containerColor = MaterialTheme.colorScheme.surface

            ) {

                val info = selectedNarratorInfo!!

                Column(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(16.dp)

                ) {

                    Text(

                        text = info.name,

                        fontSize = 18.sp,

                        fontWeight = FontWeight.ExtraBold,

                        color = headings,

                        textAlign = TextAlign.Right,

                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = info.shortBio,

                        fontSize = 14.sp,

                        color = MaterialTheme.colorScheme.onSurface,

                        textAlign = TextAlign.Right,

                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(

                        text = "معلومات مختصرة",

                        fontSize = 14.sp,

                        fontWeight = FontWeight.Bold,

                        color = headings,

                        textAlign = TextAlign.Right,

                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    info.points.forEach { p ->

                        Text(

                            text = "• $p",

                            fontSize = 13.sp,

                            color = MaterialTheme.colorScheme.onSurfaceVariant,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }

            }

        }

        Box(

            modifier = Modifier

                .fillMaxSize()

                .background(bg)

                .padding(horizontal = 16.dp)

                .statusBarsPadding()

        ) {

            Column(

                modifier = Modifier

                    .fillMaxSize()

                    .verticalScroll(rememberScrollState())

            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Row(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(top = 6.dp),

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    IconButton(

                        onClick = onBackClick,

                        modifier = Modifier.size(48.dp)

                    ) {

                        Icon(

                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,

                            contentDescription = "Back",

                            tint = headings

                        )

                    }

                    Text(

                        text = "تفاصيل الحديث",

                        fontSize = 18.sp,

                        fontWeight = FontWeight.ExtraBold,

                        color = headings,

                        modifier = Modifier.weight(1f),

                        textAlign = TextAlign.Right

                    )

                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(

                    modifier = Modifier

                        .fillMaxWidth()

                        .height(350.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(

                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.35f else 0.60f)

                    ),

                    elevation = CardDefaults.cardElevation(0.dp)

                ) {

                    if (meta != null) {

                        Image(

                            painter = painterResource(id = meta.imageResId),

                            contentDescription = meta.title,

                            modifier = Modifier

                                .fillMaxSize()

                                .clip(RoundedCornerShape(18.dp)),

                            contentScale = ContentScale.Crop

                        )

                    } else {

                        Spacer(modifier = Modifier.fillMaxSize())

                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

                Card(

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(

                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.70f else 0.78f)

                    ),

                    elevation = CardDefaults.cardElevation(0.dp),

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        if (meta == null) {

                            Text(

                                text = "لم يتم العثور على الحديث.",

                                fontSize = 14.sp,

                                color = subtleText,

                                textAlign = TextAlign.Right,

                                modifier = Modifier.fillMaxWidth()

                            )

                            return@Column

                        }

                        Text(

                            text = meta.title,

                            fontSize = 20.sp,

                            fontWeight = FontWeight.ExtraBold,

                            color = headings,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        AssistChip(

                            onClick = {

                                selectedNarratorInfo = narratorsInfo[meta.narrator]

                                if (selectedNarratorInfo != null) {

                                    showSheet = true

                                    scope.launch { sheetState.show() }

                                }

                            },

                            label = {

                                Text(

                                    text = "الراوي: ${meta.narrator}",

                                    fontSize = 13.sp,

                                    color = contentColor,

                                    fontWeight = FontWeight.SemiBold

                                )

                            },

                            colors = AssistChipDefaults.assistChipColors(

                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (!isDark) 0.75f else 0.90f)

                            ),

                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)

                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(

                            text = "نص الحديث",

                            fontSize = 14.sp,

                            fontWeight = FontWeight.Bold,

                            color = headings,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(

                            text = meta.simpleMatn,

                            fontSize = 16.sp,

                            fontWeight = FontWeight.Medium,

                            color = MaterialTheme.colorScheme.onSurface,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(

                            text = apiMatn,

                            fontSize = 14.sp,

                            fontWeight = FontWeight.Medium,

                            color = MaterialTheme.colorScheme.onSurfaceVariant,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(

                            text = "ماذا نفهم من الحديث؟",

                            fontSize = 14.sp,

                            fontWeight = FontWeight.Bold,

                            color = headings,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(

                            text = meta.kidsExplain,

                            fontSize = 14.sp,

                            color = MaterialTheme.colorScheme.onSurface,

                            textAlign = TextAlign.Right,

                            modifier = Modifier.fillMaxWidth()

                        )

                    }

                }

                Spacer(modifier = Modifier.height(24.dp))

            }

        }

    }

}
