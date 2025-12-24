package com.norah.nooralhadith.data.mapper

import com.norah.nooralhadith.data.dto.HadithDetailsDto

import com.norah.nooralhadith.data.local.HadithMeta

import com.norah.nooralhadith.domain.model.HadithDetailsModel

/**

 * ✅ API يرجع نص الحديث (matn) فقط

 * ✅ باقي البيانات (العنوان + الراوي + الشرح + الصورة) ناخذها من Local HadithMeta

 */

fun HadithDetailsDto.toModel(meta: HadithMeta): HadithDetailsModel {

    return HadithDetailsModel(

        id = id,

        title = meta.title,

        narrator = meta.narrator,

        matn = matn,

        kidsExplain = meta.kidsExplain,

        imageResId = meta.imageResId

    )

}
