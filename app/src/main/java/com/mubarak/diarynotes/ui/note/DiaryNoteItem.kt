package com.mubarak.diarynotes.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.diarynotes.data.sources.local.model.Note
import com.mubarak.diarynotes.ui.theme.DiaryTheme

@Composable
fun DiaryNoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onItemClick: (Note) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                onItemClick(note)
            }),
    )
    {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall,
            )

        }
    }
}

@Preview
@Composable
private fun DiaryNoteItemPreview() {
    DiaryTheme {
        DiaryNoteItem(
          note = Note(
              "1",
              "Title for your note",
              "Description for your note")
        ){}
    }
}