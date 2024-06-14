package com.mubarak.diarynotes.ui.addoredit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    viewModel: ActionNoteViewModel = hiltViewModel()
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    DiaryTheme {
        Scaffold(modifier = modifier, snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }, topBar = {
            AddEditTopAppBar(
                onUpButtonClick = onUpButtonClick
            )
        }, floatingActionButton = {
           SaveFab(onFabClick = viewModel::saveNote)
        }) {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            DiaryNoteFields(
                modifier = Modifier.padding(it),
                title = viewModel.title,
                description = viewModel.description,
                onTitleChange = viewModel::updateTitle,
                onDescriptionChange = viewModel::updateDescription
            )

            LaunchedEffect(
                uiState.navigateToHome
            ) {
                if (uiState.navigateToHome){
                    navigateToHome()
                }
            }

            if (uiState.message != null) {
                val message = stringResource(id = uiState.message!!)
                LaunchedEffect(uiState.message) {
                    snackBarHostState.showSnackbar(message)
                }
            }

        }
    }
}

@Composable
fun DiaryNoteFields(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onTitleChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {}
) {

    Column(modifier = modifier) {
        val textFieldColour = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
        )
        TextField(
            value = title,
            onValueChange = {
                onTitleChange(it)
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

@Composable
fun SaveFab(modifier: Modifier = Modifier, onFabClick: () -> Unit) {
    FloatingActionButton(onClick = onFabClick, modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.save)
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
        IconButton(onClick = onUpButtonClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.nav_back)
            )
        }
    })
}
