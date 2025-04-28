package co.feip.fefu2025.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import co.feip.fefu2025.presentation.git_repository_detail_screen.GitRepositoryDetailScreen
import co.feip.fefu2025.presentation.git_repository_list_screen.GitRepositoryListScreen
import co.feip.fefu2025.presentation.my_stars_screen.MyStarsScreen
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.EventObserver
import co.feip.fefu2025.presentation.navigation.NavigationAction
import co.feip.fefu2025.presentation.navigation.Navigator
import co.feip.fefu2025.presentation.ui.theme.FEFU2025AndroidBaseRepoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    EventObserver(flow = navigator.navigationActions) { action ->
                        when(action) {
                            is NavigationAction.Navigate -> navController.navigate(
                                action.destination
                            ) {
                                action.navOptions(this)
                            }
                            NavigationAction.NavigationUp -> navController.navigateUp()
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = navigator.startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        navigation<Destination.MainGraph>(
                            startDestination = Destination.GitRepositoryListScreen
                        ) {
                            composable<Destination.GitRepositoryListScreen> {
                                GitRepositoryListScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    navigator = navigator
                                )
                            }
                            composable<Destination.GitRepositoryDetailScreen>(
                                deepLinks = listOf(
                                    navDeepLink {
                                        uriPattern = "mysuperapp://repo/{id}"
                                        action = Intent.ACTION_VIEW
                                    }
                                )
                            ) {
                                val args = it.toRoute<Destination.GitRepositoryDetailScreen>()
                                GitRepositoryDetailScreen(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(15.dp),
                                    navigator = navigator,
                                    gitRepositoryId = args.id
                                )
                            }
                            composable<Destination.MyStarsScreen> {
                                MyStarsScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    navigator = navigator
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}