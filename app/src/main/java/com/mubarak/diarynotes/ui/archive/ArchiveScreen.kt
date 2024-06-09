package com.mubarak.diarynotes.ui.archive

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
import androidx.compose.ui.tooling.preview.Preview
import com.mubarak.diarynotes.ui.note.DiaryNoteItem
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun ArchiveScreen(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
) {

    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            AddEditTopAppBar {
                onMenuClick()
            }
        }) {
            LazyDiaryNoteItems(
                modifier = Modifier.padding(it),
                noteItems = fakeNoteItem
            )
        }
    }
}

data class NoteItem(
    val title: String,
    val description: String
)

// TODO replace it with real room database
val fakeNoteItem = listOf(
    com.mubarak.diarynotes.data.sources.local.model.Note(
        id = 1,
        title = "Title 1",
        description = "Description 1"
    ),
    com.mubarak.diarynotes.data.sources.local.model.Note(
        id = 2,
        title = "Title 2",
        description = "Description 2"
    ),
    com.mubarak.diarynotes.data.sources.local.model.Note(
        id = 3,
        title = "Title 3",
        description = "Description 3"
    )
)

@Composable
fun LazyDiaryNoteItems(
    modifier: Modifier = Modifier,
    noteItems: List<com.mubarak.diarynotes.data.sources.local.model.Note>
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
fun AddEditTopAppBar(modifier: Modifier = Modifier, onMenuClick: () -> Unit) {
    TopAppBar(title = {
        Text(text = "Archive")
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
        ArchiveScreen {}
    }
}