package com.mubarak.diarynotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun DiaryAppDrawer(
    modifier: Modifier = Modifier,
    currentDestination: Any,
    navigateToNotes: () -> Unit,
    navigateToArchive: () -> Unit,
    navigateToDeleted: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = modifier
    ) {
        NavigationHeader()
        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.notes))
            },
            selected = currentDestination == DiaryHomeDestination()::class.qualifiedName,
            onClick = {
                navigateToArchive(); closeDrawer()
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.FormatListBulleted,
                    contentDescription = null
                )
            })

        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.archive))
            },
            selected = currentDestination == ArchiveDestination::class.qualifiedName,
            onClick = {
                navigateToNotes(); closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Archive, contentDescription = null) }
        )

        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.deleted))
            },
            selected = currentDestination == DeletedDestination::class.qualifiedName,
            onClick = { navigateToDeleted(); closeDrawer() },
            icon = { Icon(imageVector = Icons.Default.Delete, contentDescription = null) }
        )
    }
}

@Composable
fun NavigationHeader(modifier: Modifier = Modifier) {

    Box(modifier = modifier.padding(horizontal = 28.dp, vertical = 24.dp)) {
        Text(
            text = stringResource(R.string.diary),
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NavHeaderPreview() {
    DiaryTheme {
        DiaryAppDrawer(
            navigateToArchive = {},
            navigateToNotes = {},
            navigateToDeleted = {},
            closeDrawer = {},
            currentDestination = DiaryHomeDestination().toString()
        )
    }
}