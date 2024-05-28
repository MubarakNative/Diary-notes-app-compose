package com.mubarak.diarynotes.ui.note

import android.content.res.Configuration
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.archive.NoteItem
import com.mubarak.diarynotes.ui.archive.fakeNoteItem
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun DiaryHomeScreen(
    modifier: Modifier = Modifier,
    onDrawer: () -> Unit,
    onSearchActionClick: () -> Unit = {},
    onFabClick: () -> Unit,
) {
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
        LazyDiaryNoteItems(noteItems = fakeNoteItem, modifier = Modifier.padding(it))
    }
}

@Composable
fun LazyDiaryNoteItems(
    modifier: Modifier = Modifier,
    noteItems: List<NoteItem>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            noteItems
        ) {
            DiaryNoteItem(title = it.title, description = it.description)
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
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.createNote)
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DiaryAppPreview() {
    DiaryTheme {
        LazyDiaryNoteItems(noteItems = fakeNoteItem)
    }
}

@Preview
@Composable
fun DiaryNavDrawerPreview() {
    DiaryTheme {
        DiaryHomeScreen(onDrawer = {}, onSearchActionClick = {}, onFabClick = {})
    }
}
