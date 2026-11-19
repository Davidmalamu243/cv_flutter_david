package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.ContactPhone
import androidx.compose.material.icons.outlined.FamilyRestroom
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.model.AppDestination
import com.example.ui.components.CleanProBackdrop
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.CvScreen
import com.example.ui.screens.FamilyScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.theme.CvDavidTheme
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            CvDavidTheme(darkTheme = isDarkTheme) {
                CvDavidApp(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme }
                )
            }
        }
    }
}

private data class NavTabItem(
    val destination: AppDestination,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

private val mainTabs = listOf(
    NavTabItem(
        destination = AppDestination.Cv,
        labelRes = R.string.nav_cv,
        selectedIcon = Icons.Filled.Badge,
        unselectedIcon = Icons.Outlined.Badge,
        testTag = "nav_tab_cv"
    ),
    NavTabItem(
        destination = AppDestination.Family,
        labelRes = R.string.nav_family,
        selectedIcon = Icons.Filled.FamilyRestroom,
        unselectedIcon = Icons.Outlined.FamilyRestroom,
        testTag = "nav_tab_family"
    ),
    NavTabItem(
        destination = AppDestination.Contact,
        labelRes = R.string.nav_contact,
        selectedIcon = Icons.Filled.ContactPhone,
        unselectedIcon = Icons.Outlined.ContactPhone,
        testTag = "nav_tab_contact"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CvDavidApp(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val loginState by mainViewModel.loginState.collectAsStateWithLifecycle()
    val selectedFilter by mainViewModel.selectedCvFilter.collectAsStateWithLifecycle()
    val familyLayoutMode by mainViewModel.familyLayoutMode.collectAsStateWithLifecycle()
    val selectedFamilyMember by mainViewModel.selectedFamilyMember.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val isAuthenticatedRoute = currentRoute != null && currentRoute != AppDestination.Login.route

    val navigateToTab: (AppDestination) -> Unit = { dest ->
        navController.navigate(dest.route) {
            popUpTo(AppDestination.Cv.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val handleLogout: () -> Unit = {
        mainViewModel.logout()
        navController.navigate(AppDestination.Login.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = isAuthenticatedRoute,
        drawerContent = {
            if (isAuthenticatedRoute) {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 24.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.img_david_profile),
                                contentDescription = "Portrait de David",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Ciza Elmalamu David",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "L4 ISIG-Goma • Quartier Himbi",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    Spacer(modifier = Modifier.height(8.dp))

                    mainTabs.forEach { tab ->
                        val selected = currentRoute == tab.destination.route
                        NavigationDrawerItem(
                            label = { Text(stringResource(tab.labelRes)) },
                            selected = selected,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navigateToTab(tab.destination)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                                .testTag("drawer_${tab.testTag}")
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.action_logout)) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            handleLogout()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Logout,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                            .testTag("drawer_logout")
                    )
                }
            }
        }
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val useNavigationRail = isAuthenticatedRoute && maxWidth >= 600.dp

            Scaffold(
                containerColor = Color.Transparent,
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                topBar = {
                    if (isAuthenticatedRoute) {
                        val titleText = when (currentRoute) {
                            AppDestination.Cv.route -> stringResource(R.string.nav_cv)
                            AppDestination.Family.route -> stringResource(R.string.nav_family)
                            AppDestination.Contact.route -> stringResource(R.string.nav_contact)
                            else -> stringResource(R.string.app_name)
                        }
                        Column {
                            CenterAlignedTopAppBar(
                                title = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = titleText,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = "Ciza Elmalamu David",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = { scope.launch { drawerState.open() } },
                                        modifier = Modifier.testTag("drawer_menu_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = stringResource(R.string.action_menu)
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(
                                        onClick = onToggleTheme,
                                        modifier = Modifier.testTag("toggle_theme_button")
                                    ) {
                                        Icon(
                                            imageVector = if (isDarkTheme) {
                                                Icons.Default.LightMode
                                            } else {
                                                Icons.Default.DarkMode
                                            },
                                            contentDescription = "Changer le thème"
                                        )
                                    }
                                    IconButton(
                                        onClick = handleLogout,
                                        modifier = Modifier.testTag("logout_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.Logout,
                                            contentDescription = stringResource(R.string.action_logout),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f))
                        }
                    }
                },
                bottomBar = {
                    if (isAuthenticatedRoute && !useNavigationRail) {
                        Column {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f))
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                windowInsets = WindowInsets.navigationBars
                            ) {
                                mainTabs.forEach { tab ->
                                    val selected = currentRoute == tab.destination.route
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { navigateToTab(tab.destination) },
                                        icon = {
                                            Icon(
                                                imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = stringResource(tab.labelRes)
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(tab.labelRes),
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                        ),
                                        modifier = Modifier.testTag(tab.testTag)
                                    )
                                }
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    CleanProBackdrop()

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (useNavigationRail) {
                            NavigationRail(
                                containerColor = MaterialTheme.colorScheme.surface
                            ) {
                                Spacer(modifier = Modifier.height(12.dp))
                                mainTabs.forEach { tab ->
                                    val selected = currentRoute == tab.destination.route
                                    NavigationRailItem(
                                        selected = selected,
                                        onClick = { navigateToTab(tab.destination) },
                                        icon = {
                                            Icon(
                                                imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = stringResource(tab.labelRes)
                                            )
                                        },
                                        label = { Text(stringResource(tab.labelRes)) },
                                        modifier = Modifier.testTag("rail_${tab.testTag}")
                                    )
                                }
                            }
                        }

                        NavHost(
                            navController = navController,
                            startDestination = AppDestination.Login.route,
                            modifier = Modifier.weight(1f)
                        ) {
                            composable(AppDestination.Login.route) {
                                LoginScreen(
                                    uiState = loginState,
                                    onIdentifierChange = mainViewModel::onIdentifierChanged,
                                    onPasswordChange = mainViewModel::onPasswordChanged,
                                    onTogglePasswordVisibility = mainViewModel::togglePasswordVisibility,
                                    onFillDemoClick = mainViewModel::fillDemoCredentials,
                                    onSubmitLogin = {
                                        if (mainViewModel.submitLogin()) {
                                            navController.navigate(AppDestination.Cv.route) {
                                                popUpTo(AppDestination.Login.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                )
                            }

                            composable(AppDestination.Cv.route) {
                                CvScreen(
                                    profile = mainViewModel.profile,
                                    experiences = mainViewModel.experiences,
                                    education = mainViewModel.education,
                                    skills = mainViewModel.technicalSkills,
                                    languages = mainViewModel.languages,
                                    interests = mainViewModel.interests,
                                    selectedFilter = selectedFilter,
                                    onSelectFilter = mainViewModel::selectCvFilter
                                )
                            }

                            composable(AppDestination.Family.route) {
                                FamilyScreen(
                                    familyMembers = mainViewModel.familyMembers,
                                    layoutMode = familyLayoutMode,
                                    selectedMember = selectedFamilyMember,
                                    onLayoutModeChange = mainViewModel::setFamilyLayoutMode,
                                    onSelectMember = mainViewModel::selectFamilyMember
                                )
                            }

                            composable(AppDestination.Contact.route) {
                                ContactScreen(
                                    contacts = mainViewModel.contacts,
                                    shareSummary = mainViewModel.getShareSummary(),
                                    onShowMessage = { msg ->
                                        scope.launch {
                                            snackbarHostState.showSnackbar(msg)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
