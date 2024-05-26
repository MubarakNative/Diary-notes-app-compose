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
fun ArchiveScreen(modifier: Modifier = Modifier) {

    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            AddEditTopAppBar()
        }) {
            LazyDiaryNoteItems(
                modifier = Modifier.padding(it),
                noteItems = noteItems
            )
        }
    }
}

data class NoteItem(
    val title: String,
    val description: String
)

val noteItems = listOf(
    NoteItem(
        title = "Title 1",
        description = "Description 1"
    ),
    NoteItem(
        title = "Title 2",
        description = "Description 2"
    ),
    NoteItem(
        title = "Title 3",
        description = "Description 3"
    )
)

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
fun AddEditTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(text = "Archive")
    }, modifier = modifier, navigationIcon = {

        IconButton(onClick = { /*TODO Open nav drawer*/ }) {
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
        ArchiveScreen()
    }
}