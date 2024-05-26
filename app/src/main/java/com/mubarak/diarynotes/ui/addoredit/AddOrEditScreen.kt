package com.mubarak.diarynotes.ui.addoredit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun AddEditScreen(modifier: Modifier = Modifier) {

    DiaryTheme {
        Scaffold(modifier = modifier, topBar = {
            AddEditTopAppBar()
        }) {
            DiaryNoteFields(modifier = Modifier.padding(it))
        }
    }
}

@Composable
fun DiaryNoteFields(
    modifier: Modifier = Modifier,
    title: String = "",
    onTitleChange: (String) -> Unit = {},
    description: String = "",
    onDescriptionChange: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        TextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                Text(text = "Title")
            },
        )

        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = {
                Text(text = "Description")
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {

    }, modifier = modifier, navigationIcon = {

        IconButton(onClick = { /*TODO: NavController.POPBACKSTACK*/ }) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.nav_back)
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