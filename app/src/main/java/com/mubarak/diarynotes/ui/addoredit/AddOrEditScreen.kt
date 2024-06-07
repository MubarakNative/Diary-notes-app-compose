package com.mubarak.diarynotes.ui.addoredit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {}
) {

    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            AddEditTopAppBar(onUpButtonClick = onUpButtonClick)
        }) {
            DiaryNoteFields(modifier = Modifier.padding(it))
        }
    }
}

@Composable
fun DiaryNoteFields(
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {}
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier) {
        val textFieldColour = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
        )
        TextField(
            value = title,
            onValueChange = {
                onTitleChange(it)
                title = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.title))
            },
            colors = textFieldColour
        )

        TextField(
            value = description,
            onValueChange = {
                onDescriptionChange(it)
                description = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = {
                Text(text = stringResource(R.string.description))
            },
            colors = textFieldColour
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopAppBar(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {}
) {
    TopAppBar(title = {

    }, modifier = modifier, navigationIcon = {
        IconButton(onClick = {
            onUpButtonClick()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.nav_back)
            )
        }
    }, actions = {
        IconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.save)
            )
        }
    })
}

@Preview
@Composable
private fun AddEditScreenPreview() {
    DiaryTheme {
        AddEditScreen()
    }
}