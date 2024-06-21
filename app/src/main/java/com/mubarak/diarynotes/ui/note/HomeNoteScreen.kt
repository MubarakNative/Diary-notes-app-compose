package com.mubarak.diarynotes.ui.note

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.data.sources.local.model.Note
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DiaryHomeScreen( // TODO: Replace this with compositionLocal for pass arguments on deeply nested hierarchy
    modifier: Modifier = Modifier,
    onDrawer: () -> Unit,
    onSearchActionClick: () -> Unit = {},
    onFabClick: () -> Unit,
    onItemClick: (Note) -> Unit = {},
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeNoteViewModel = hiltViewModel()
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        topBar = {
            DiaryTopAppBar(
                modifier = modifier,
                onMenuClick = onDrawer,
                searchActionClick = onSearchActionClick
            )
        },
        floatingActionButton = {
            DiaryFab(onFabClick = {
                onFabClick()
            })
        }
    ) {

        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        if (uiState.value.message != null) {
            val message = stringResource(id = uiState.value.message!!)
            LaunchedEffect(uiState.value.message) {
                snackBarHostState.showSnackbar(message)
            }
        }

        LazyDiaryNoteItems(
            noteItems = uiState.value.notes,
            onItemClick = onItemClick,
            animatedVisibilityScope = animatedVisibilityScope,
            modifier = Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LazyDiaryNoteItems(
    modifier: Modifier = Modifier,
    noteItems: List<Note>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Note) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            noteItems
        ) {
            DiaryNoteItem(
                note = it,
                onItemClick = onItemClick,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit,
    searchActionClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = searchActionClick) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DiaryFab(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit
) {
    FloatingActionButton(onClick = {
        onFabClick()
    }, modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.createNote)
        )
    }
}
