package com.mubarak.diarynotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.diarynotes.DiaryDestination.ADD_EDIT_ROUTE
import com.mubarak.diarynotes.DiaryDestination.ARCHIVE_ROUTE
import com.mubarak.diarynotes.DiaryDestination.DELETED_ROUTE
import com.mubarak.diarynotes.DiaryDestination.NOTES_ROUTE
import com.mubarak.diarynotes.DiaryDestination.SEARCH_ROUTE
import com.mubarak.diarynotes.ui.addoredit.AddEditScreen
import com.mubarak.diarynotes.ui.archive.ArchiveScreen
import com.mubarak.diarynotes.ui.deleted.DeletedNoteScreen
import com.mubarak.diarynotes.ui.note.DiaryHomeScreen
import com.mubarak.diarynotes.ui.search.SearchNoteScreen

@Composable
fun DiaryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onDrawerClicked: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = NOTES_ROUTE
    ) {
        composable(NOTES_ROUTE) {
            DiaryHomeScreen(
                modifier = modifier,
                onDrawer = { onDrawerClicked() },
                onSearchActionClick = {
                    navController.navigate(SEARCH_ROUTE)
                },
                onFabClick = {
                    navController.navigate(ADD_EDIT_ROUTE)
                }
            )
        }
        composable(ARCHIVE_ROUTE) {
            ArchiveScreen{
                onDrawerClicked()
            }
        }
        composable(DELETED_ROUTE) {
            DeletedNoteScreen{
                onDrawerClicked()
            }
        }
        composable(SEARCH_ROUTE){
            SearchNoteScreen(modifier = modifier, onUpButtonClick = {
                navController.navigateUp()
            })
        }
        composable(ADD_EDIT_ROUTE){
            AddEditScreen(modifier = modifier, onUpButtonClick = {
                navController.navigateUp()
            })
        }
    }
}
