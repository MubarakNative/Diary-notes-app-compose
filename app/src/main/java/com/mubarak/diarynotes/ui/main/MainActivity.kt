package com.mubarak.diarynotes.ui.main

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mubarak.diarynotes.R
import com.mubarak.diarynotes.ui.theme.DiaryTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiaryTheme {
                DiaryNavDrawer()
            }
        }
    }
}

data class DiaryTopLevelDestination(
    val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val title: String
)

val TOP_LEVEL_DESTINATIONS = listOf(
    DiaryTopLevelDestination(
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        title = "Notes"
    ),
    DiaryTopLevelDestination(
        selectedIcon = Icons.Filled.Archive,
        unselectedIcon = Icons.Outlined.Archive,
        title = "Archive"
    ),
    DiaryTopLevelDestination(
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        title = "Settings"
    )
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DiaryNavDrawer(modifier: Modifier = Modifier) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                // replace it with the compose navigation implementation
                TOP_LEVEL_DESTINATIONS.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = selected == index,
                        onClick = {
                            selected = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selected) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                DiaryTopAppBar(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                })
            },
            floatingActionButton = {
                DiaryFab()
            }
        ) {
            // TODO Implement the content
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryTopAppBar(
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO Go to search screen*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DiaryFab(modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = {
        // TODO: Navigate to Add/Edit Screen
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.createNote)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DiaryAppPreview() {
    DiaryTheme {
        DiaryTopAppBar {}
    }
}

@Preview
@Composable
fun DiaryNavDrawerPreview() {
    DiaryTheme {
        DiaryNavDrawer()
    }
}