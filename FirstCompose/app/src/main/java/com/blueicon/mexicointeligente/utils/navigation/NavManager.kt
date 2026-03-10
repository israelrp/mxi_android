package com.blueicon.mexicointeligente.utils.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blueicon.mexicointeligente.business.enrolment.entercode.EnterCodeView
import com.blueicon.mexicointeligente.business.enrolment.onboarding.OnboardingView
import com.blueicon.mexicointeligente.business.enrolment.phonenumber.PhoneNumberViewModel
import com.blueicon.mexicointeligente.business.enrolment.phonenumber.PhonenumberView
import com.blueicon.mexicointeligente.business.enrolment.splash.SplashView
import com.blueicon.mexicointeligente.business.home.HomeView
import com.blueicon.mexicointeligente.business.home.HomeViewModel
import com.blueicon.mexicointeligente.business.menunavigation.MenuNavigationView
import com.blueicon.mexicointeligente.business.myaccount.MyAccountView
import com.blueicon.mexicointeligente.business.myaccount.adminteam.AdminTeamView
import com.blueicon.mexicointeligente.business.myaccount.adminteam.AdminTeamViewModel
import com.blueicon.mexicointeligente.business.myaccount.bankdetail.BankDetailView
import com.blueicon.mexicointeligente.business.myaccount.configuration.ConfigurationView
import com.blueicon.mexicointeligente.business.myaccount.createadvisor.CreateAdvisorView
import com.blueicon.mexicointeligente.business.myaccount.realstate.RealStateView
import com.blueicon.mexicointeligente.business.myaccount.termsconditions.TermsConditionsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsViewModel
import com.blueicon.mexicointeligente.business.myearnings.earningdetail.EarningDetailView
import com.blueicon.mexicointeligente.business.steps.five.StepFiveView
import com.blueicon.mexicointeligente.business.steps.four.StepFourView
import com.blueicon.mexicointeligente.business.steps.one.StepOneView
import com.blueicon.mexicointeligente.business.steps.six.StepSixView
import com.blueicon.mexicointeligente.business.steps.tree.StepTreeView
import com.blueicon.mexicointeligente.business.steps.two.StepTwoView
import com.blueicon.mexicointeligente.views.DetailView
import com.blueicon.mexicointeligente.views.RegisterView
import com.blueicon.mexicointeligente.views.WelcomeView
import com.blueicon.mexicointeligente.views.login.LoginView
import com.blueicon.mexicointeligente.views.login.LoginViewModel
import com.blueicon.mexicointeligente.views.maintwo.MainTwoView

/*val LocalNavStack = staticCompositionLocalOf <NavBackStack<NavKey>> {
    error("Nose puede inicializar")
}

@Serializable
object Splash : NavKey

@Serializable
object Onboarding : NavKey

@Serializable
object PhoneNumber : NavKey

@Serializable
object EnterCode : NavKey

@Serializable
object MenuNavigation : NavKey

@Serializable
object Configuration : NavKey

@Serializable
object BankDetail : NavKey

@Serializable
object RealState : NavKey

@Serializable
object TermsConditions : NavKey

@Serializable
object AdminTeam : NavKey

@Serializable
object CreateAdvisor : NavKey

@Serializable
data class EarningDetail(val id: Int) : NavKey*/

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
    /*val backStack = rememberNavBackStack(Splash)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        // Essential decorators for production apps
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        // Entry provider using DSL
        entryProvider = entryProvider {
            entry<Splash> {
                SplashView(navController)
            }
            entry<Onboarding> {
                OnboardingView(navController)
            }
            entry<PhoneNumber> {
                PhonenumberView(navController, phoneNumberViewModel)
            }
            entry<EnterCode> {
                EnterCodeView(navController, phoneNumberViewModel)
            }
            entry<MenuNavigation> {
                MenuNavigationView(navController, homeViewModel, myearningsViewModel)
            }
            entry<Configuration> {
                ConfigurationView(navController)
            }
            entry<BankDetail> {
                BankDetailView(navController)
            }
            entry<RealState> {
                RealStateView(navController)
            }
            entry<TermsConditions> {
                TermsConditionsView(navController)
            }
            entry<AdminTeam> {
                AdminTeamView(navController, adminTeamViewModel)
            }
            entry<CreateAdvisor> {
                CreateAdvisorView(navController)
            }
            entry<EarningDetail> { key ->
                EarningDetailView(navController, key.id)
            }
        },
        // Smooth animations
        transitionSpec = {
            slideInHorizontally { it } togetherWith
                    slideOutHorizontally { -it }
        },
        popTransitionSpec = {
            slideInHorizontally { -it } togetherWith
                    slideOutHorizontally { it }
        }
    )*/

    NavHost(
        navController = navController,
        startDestination = "Splash",
        )
    {
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
        composable("home") {
            HomeView(navController, homeViewModel)
        }
        composable("myearnings") {
            MyearningsView(navController, myearningsViewModel)
        }
        composable("myaccount") {
            MyAccountView(navController)
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
        composable("StepOne") {
            StepOneView(navController)
        }
        composable("StepTwo") {
            StepTwoView(navController)
        }
        composable("StepTree") {
            StepTreeView(navController)
        }
        composable("StepFour") {
            StepFourView(navController)
        }
        composable("StepFive") {
            StepFiveView(navController)
        }
        composable("StepSix") {
            StepSixView(navController)
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