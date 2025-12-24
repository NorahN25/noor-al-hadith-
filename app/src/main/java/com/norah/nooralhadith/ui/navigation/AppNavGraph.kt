package com.norah.nooralhadith.ui.navigation

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.norah.nooralhadith.R
import com.norah.nooralhadith.data.local.hadithMetaList
import com.norah.nooralhadith.ui.screens.HadithDetailsScreen
import com.norah.nooralhadith.ui.screens.HadithListScreen
import com.norah.nooralhadith.ui.screens.HomeScreen
import com.norah.nooralhadith.ui.screens.QuizScreen
import com.norah.nooralhadith.ui.screens.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    // ✅ أصوات الاختبار من res/raw
    val context = LocalContext.current
    val clapPlayer = remember { MediaPlayer.create(context, R.raw.clap) }
    val tryAgainPlayer = remember { MediaPlayer.create(context, R.raw.try_again) }

    // ✅ تنظيف (release) عشان ما يصير تسريب بالذاكرة
    DisposableEffect(Unit) {
        onDispose {
            clapPlayer.release()
            tryAgainPlayer.release()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onFinish = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // ✅ HOME
        composable(Routes.HOME) {
            HomeScreen(
                onHadithListClick = { navController.navigate(Routes.HADITH_LIST) { launchSingleTop = true } },
                onQuizClick = { navController.navigate(Routes.QUIZ) { launchSingleTop = true } }
            )
        }

        // ✅ LIST = قائمة الأحاديث
        composable(Routes.HADITH_LIST) {
            HadithListScreen(
                onBackClick = { navController.popBackStack() },
                onHadithClick = { id ->
                    navController.navigate(Routes.detailsRoute(id)) {
                        launchSingleTop = true
                    }
                },
                hadithList = hadithMetaList
            )
        }

        // ✅ DETAILS
        composable(
            route = Routes.DETAILS_PATTERN,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: -1

            // ✅ لو id غلط لا تدخل شاشة التفاصيل (هذا يمنع crash)
            if (id == -1) {
                navController.popBackStack()
                return@composable
            }

            HadithDetailsScreen(
                id = id,
                onBackClick = { navController.popBackStack() }
            )
        }

        // ✅ QUIZ
        composable(Routes.QUIZ) {
            QuizScreen(
                onBackClick = { navController.popBackStack() },
                onCorrectAnswer = {
                    try {
                        clapPlayer.seekTo(0)
                        clapPlayer.start()
                    } catch (_: Exception) {}
                },
                onWrongAnswer = {
                    try {
                        tryAgainPlayer.seekTo(0)
                        tryAgainPlayer.start()
                    } catch (_: Exception) {}
                }
            )
        }
    }
}