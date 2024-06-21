package com.mubarak.diarynotes

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.diarynotes.ui.addoredit.AddEditScreen
import com.mubarak.diarynotes.ui.archive.ArchiveScreen
import com.mubarak.diarynotes.ui.deleted.DeletedNoteScreen
import com.mubarak.diarynotes.ui.note.DiaryHomeScreen
import com.mubarak.diarynotes.ui.search.SearchNoteScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DiaryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onDrawerClicked: () -> Unit,
) {

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = DiaryHomeDestination
        ) {
            composable<DiaryHomeDestination> {
                DiaryHomeScreen(
                    modifier = modifier,
                    onDrawer = { onDrawerClicked() },
                    onItemClick = { note ->
                        navController.navigate(AddEditDestination(note.id))
                    },
                    onSearchActionClick = {
                        navController.navigate(SearchDestination)
                    },
                    animatedVisibilityScope = this,
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
                DeletedNoteScreen(
                    animatedVisibilityScope = this,
                    onMenuClick = {
                        onDrawerClicked()
                    }
                )
            }
            composable<SearchDestination> {
                SearchNoteScreen(modifier = modifier, onUpButtonClick = {
                    navController.navigateUp()
                }, animatedVisibilityScope = this)
            }
            composable<AddEditDestination> {
                AddEditScreen(onUpButtonClick = {
                    /** Sometimes the up button is not working as intended in the beta version
                     * of navigation compose the bug is already reported on issue tracker
                     * TODO See: https://issuetracker.google.com/issues/347114499
                     * */
                    navController.navigateUp()
                }, navigateToHome = {
                    navController.navigate(DiaryHomeDestination)
                }, animatedVisibilityScope = this)
            }
        }


    }
}


