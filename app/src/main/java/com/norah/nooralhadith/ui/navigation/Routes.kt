object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val HADITH_LIST = "hadith_list"

    const val DETAILS = "details"
    const val DETAILS_PATTERN = "details/{id}"
    fun detailsRoute(id: Int) = "details/$id"

    const val QUIZ = "quiz"
}