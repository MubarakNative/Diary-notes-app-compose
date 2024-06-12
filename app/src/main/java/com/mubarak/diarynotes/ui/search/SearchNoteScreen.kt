package com.mubarak.diarynotes.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.deleted.LazyDiaryNoteItems
import com.mubarak.diarynotes.ui.deleted.noteItems
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun SearchNoteScreen(modifier: Modifier = Modifier, onUpButtonClick: () -> Unit = {}) {
    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            SearchNoteTopAppBar(modifier = modifier, onUpButtonClick = {
                onUpButtonClick()
            })
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
    onValueChange: (String) -> Unit = {},
    onUpButtonClick: () -> Unit = {}
) {
    val brush = remember {
        Brush.linearGradient(
            colors = listOf<Color>(Cyan, Blue)
        )
    }
    var query by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp),
        value = query,
        onValueChange = {
            onValueChange(it)
            query = it
        },
        leadingIcon = {
            IconButton(onClick = {
                onUpButtonClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_your_note)
            )
        },
        textStyle = TextStyle(
            brush = brush
        )
    )
}

@Preview
@Composable
private fun SearchNoteScreenPreview() {
    DiaryTheme {
        SearchNoteScreen()
    }
}