package com.mubarak.diarynotes

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mubarak.diarynotes.DiaryDestination.ARCHIVE_ROUTE
import com.mubarak.diarynotes.DiaryDestination.DELETED_ROUTE
import com.mubarak.diarynotes.DiaryDestination.NOTES_ROUTE
import kotlinx.coroutines.launch

@Composable
fun DiaryApp() {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: NOTES_ROUTE

    ModalNavigationDrawer(
        drawerContent = {
            DiaryAppDrawer(
                currentDestination = currentRoute,
                navigateToNotes = {
                    navController.navigate(ARCHIVE_ROUTE) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToArchive = {
                    navController.navigate(NOTES_ROUTE){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                navigateToDeleted = {
                    navController.navigate(DELETED_ROUTE){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                },
            )
        },
        drawerState = drawerState
    ) { // container for the app content
        DiaryNavGraph(navController = navController) {
            scope.launch {
                drawerState.open()
            }
        }
    }
}