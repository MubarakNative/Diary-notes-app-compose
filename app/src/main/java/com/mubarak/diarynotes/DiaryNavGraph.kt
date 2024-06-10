package com.mubarak.diarynotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
        startDestination = DiaryHomeDestination
    ) {
        composable<DiaryHomeDestination> {
            DiaryHomeScreen(
                modifier = modifier,
                onDrawer = { onDrawerClicked() },
                onSearchActionClick = {
                    navController.navigate(SearchDestination)
                },
                onFabClick = {
                    navController.navigate(AddEditDestination(null))
                }
            )
        }
        composable<ArchiveDestination> {
            ArchiveScreen {
                onDrawerClicked()
            }
        }
        composable<DeletedDestination> {
            DeletedNoteScreen {
                onDrawerClicked()
            }
        }
        composable<SearchDestination> {
            SearchNoteScreen(modifier = modifier, onUpButtonClick = {
                navController.navigateUp()
            })
        }
        composable<AddEditDestination> { entry ->
            AddEditScreen(modifier = modifier, onUpButtonClick = {
                navController.navigateUp()
            }, noteId = entry.toRoute<AddEditDestination>().noteId)
        }
    }
}
