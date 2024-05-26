package com.mubarak.diarynotes.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.deleted.LazyDiaryNoteItems
import com.mubarak.diarynotes.ui.deleted.noteItems
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun SearchNoteScreen(modifier: Modifier = Modifier) {
    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            SearchNoteTopAppBar(modifier =modifier)
        }) {
            LazyDiaryNoteItems(
                modifier = Modifier.padding(it),
                noteItems = noteItems
            )
        }
    }
}

@Composable
fun SearchNoteTopAppBar(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = "",
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_note)
            )
        },
        placeholder = {
            Text(text = "Search your note")
        }
    )
}

@Preview
@Composable
private fun SearchNoteScreenPreview() {
    DiaryTheme {
        SearchNoteScreen()
    }
}