package com.blueicon.mexicointeligente.utils.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blueicon.mexicointeligente.business.enrolment.entercode.EnterCodeView
import com.blueicon.mexicointeligente.views.login.LoginView
import com.blueicon.mexicointeligente.views.login.LoginViewModel
import com.blueicon.mexicointeligente.views.maintwo.MainTwoView
import com.blueicon.mexicointeligente.business.enrolment.onboarding.OnboardingView
import com.blueicon.mexicointeligente.business.enrolment.phonenumber.PhoneNumberViewModel
import com.blueicon.mexicointeligente.business.enrolment.phonenumber.PhonenumberView
import com.blueicon.mexicointeligente.business.enrolment.splash.SplashView
import com.blueicon.mexicointeligente.business.home.HomeViewModel
import com.blueicon.mexicointeligente.business.menunavigation.MenuNavigationView
import com.blueicon.mexicointeligente.business.myaccount.adminteam.AdminTeamView
import com.blueicon.mexicointeligente.business.myaccount.adminteam.AdminTeamViewModel
import com.blueicon.mexicointeligente.business.myaccount.bankdetail.BankDetailView
import com.blueicon.mexicointeligente.business.myaccount.configuration.ConfigurationView
import com.blueicon.mexicointeligente.business.myaccount.createadvisor.CreateAdvisorView
import com.blueicon.mexicointeligente.business.myaccount.realstate.RealStateView
import com.blueicon.mexicointeligente.business.myaccount.termsconditions.TermsConditionsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsViewModel
import com.blueicon.mexicointeligente.business.myearnings.earningdetail.EarningDetailView
import com.blueicon.mexicointeligente.views.DetailView
import com.blueicon.mexicointeligente.views.RegisterView
import com.blueicon.mexicointeligente.views.WelcomeView

@Composable
fun NavManager(
    //viewModel: ViewModel
) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val phoneNumberViewModel: PhoneNumberViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val myearningsViewModel: MyearningsViewModel = viewModel()
    val adminTeamViewModel: AdminTeamViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "Splash",
        /*enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
        popEnterTransition = { // Transition when popping the back stack (e.g., Back button)
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000)
            )
        }*/) {
        composable("Splash") {
            SplashView(navController)
        }
        composable("Onboarding") {
            OnboardingView(navController)
        }
        composable("PhoneNumber") {
            PhonenumberView(navController, phoneNumberViewModel)
        }
        composable("EnterCode") {
            EnterCodeView(navController, phoneNumberViewModel)
        }
        composable("MenuNavigation") {
            MenuNavigationView(navController, homeViewModel, myearningsViewModel)
        }
        composable("Configuration") {
            ConfigurationView(navController)
        }
        composable("BankDetail") {
            BankDetailView(navController)
        }
        composable("RealState") {
            RealStateView(navController)
        }
        composable("TermsConditions") {
            TermsConditionsView(navController)
        }
        composable("AdminTeam") {
            AdminTeamView(navController, adminTeamViewModel)
        }
        composable("CreateAdvisor") {
            CreateAdvisorView(navController)
        }
        composable("EarningDetail/{step}", arguments = listOf(
            navArgument("step") { type = NavType.IntType }
        )) {
            val step = it.arguments?.getInt("step") ?: 0
            EarningDetailView(navController, step)
        }


        composable("Login") {
            LoginView(navController, loginViewModel)
        }
        composable("Welcome") {
            WelcomeView(navController)
        }
        composable("Register") {
            RegisterView(navController)
        }
        composable("MainTwo") {
            MainTwoView(navController)
        }
        composable("Camera") {
            //CameraAppScreen(navController)
            //VideoRecorderScreen(navController)
        }
        composable("Detail/{id}/?{optional}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("optional") { type = NavType.StringType },
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            val optiopnal = it.arguments?.getString("optional") ?: ""
            DetailView(navController, id, optiopnal)
        }
    }
}