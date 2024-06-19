package com.mubarak.diarynotes.ui.deleted

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.data.sources.local.model.Note
import com.mubarak.diarynotes.ui.note.DiaryNoteItem
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun DeletedNoteScreen(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
) {

    DiaryTheme {
        Scaffold(modifier, {
            DeletedNoteTopAppBar {
                onMenuClick()
            }
        }) {
            LazyDiaryNoteItems(
                modifier = Modifier.padding(it),
                noteItems = noteItems
            )
        }
    }
}

// TODO replace it with real room database
val noteItems = listOf(
    Note(
        id = "1",
        title = "Title 1",
        description = "Description 1"
    ),
    Note(
        id = "2",
        title = "Title 2",
        description = "Description 2"
    ),
    Note(
        id = "3",
        title = "Title 3",
        description = "Description 3"
    )
)

@Composable
fun LazyDiaryNoteItems(
    modifier: Modifier = Modifier,
    noteItems: List<Note>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            noteItems
        ) {
            DiaryNoteItem(note = it)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedNoteTopAppBar(modifier: Modifier = Modifier, onMenuClick: () -> Unit) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.trash))
    }, modifier = modifier, navigationIcon = {

        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        }
    })
}

@Preview
@Composable
private fun AddEditScreenPreview() {
    DiaryTheme {
        DeletedNoteScreen {}
    }
}