package com.norah.nooralhadith.data.local

import com.norah.nooralhadith.R

data class HadithMeta(

    val id: Int,

    val title: String,

    val narrator: String,

    val simpleMatn: String,   // 👈 نص مبسط للطفل (يعرض مباشرة)

    val kidsExplain: String,  // 👈 شرح مبسط

    val imageResId: Int,

    val collection: String,  // 👈 يستخدم للـ API

    val number: Int          // 👈 يستخدم للـ API

)

val hadithMetaList = listOf(

    HadithMeta(

        id = 1,

        title = "الكلمة الطيبة",

        narrator = "أبو هريرة",

        simpleMatn = "قال رسول الله ﷺ: الكلمة الطيبة صدقة.",

        kidsExplain = "يبين الحديث أن الكلمة الحسنة تُدخل السرور على الآخرين وتُعدّ من أعمال الخير.",

        imageResId = R.drawable.hadith_2,

        collection = "ara-bukhari",

        number = 6023

    ),

    HadithMeta(

        id = 2,

        title = "الرحمة",

        narrator = "أبو هريرة",

        simpleMatn = "قال رسول الله ﷺ: من لا يَرحم لا يُرحم.",

        kidsExplain = "يدل الحديث على أهمية الرحمة واللطف مع الناس، وأن الله يرحم الرحماء.",

        imageResId = R.drawable.hadith_1,

        collection = "ara-muslim",

        number = 2319

    ),

    HadithMeta(

        id = 3,

        title = "النظافة من الإيمان",

        narrator = "أبو مالك الأشعري",

        simpleMatn = "قال رسول الله ﷺ: الطهور شطر الإيمان.",

        kidsExplain = "يوضح الحديث أن النظافة والطهارة جزء مهم من إيمان المسلم.",

        imageResId = R.drawable.hadith_3,

        collection = "ara-muslim",

        number = 223

    ),

    HadithMeta(

        id = 4,

        title = "بر الوالدين",

        narrator = "عبد الله بن مسعود",

        simpleMatn = "سُئل النبي ﷺ: أي العمل أحب إلى الله؟ قال: الصلاة على وقتها، ثم بر الوالدين.",

        kidsExplain = "يبين الحديث أن بر الوالدين من أحب الأعمال إلى الله بعد الصلاة.",

        imageResId = R.drawable.hadith_4,

        collection = "ara-bukhari",

        number = 5970

    ),

    HadithMeta(

        id = 5,

        title = "حسن الخلق",

        narrator = "أبو الدرداء",

        simpleMatn = "قال رسول الله ﷺ: ما من شيء أثقل في الميزان من حسن الخلق.",

        kidsExplain = "يشير الحديث إلى أن حسن الخلق من أثقل الأعمال في ميزان المسلم يوم القيامة.",

        imageResId = R.drawable.hadith_5,

        collection = "ara-tirmidhi",

        number = 2002

    ),

    HadithMeta(

        id = 6,

        title = "الأمانة",

        narrator = "أنس بن مالك",

        simpleMatn = "قال رسول الله ﷺ: لا إيمان لمن لا أمانة له.",

        kidsExplain = "يدل الحديث على أهمية الأمانة ووجوب أدائها إلى أصحابها.",

        imageResId = R.drawable.hadith_6,

        collection = "ara-muslim",

        number = 143

    )

)

fun getHadithMetaById(id: Int): HadithMeta? =

    hadithMetaList.firstOrNull { it.id == id }
