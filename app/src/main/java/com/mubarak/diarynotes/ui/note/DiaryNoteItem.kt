package com.mubarak.diarynotes.ui.note

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
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


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun SharedTransitionScope.DiaryNoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Note) -> Unit = {},
) {
    val textBoundsTransform = BoundsTransform { initialBounds, targetBounds ->
        keyframes {
            durationMillis = 221
            initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
            targetBounds at 221
        }
    }
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
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "title:${note.title}"), // Step:3
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = textBoundsTransform,
                )
                /*.sharedElement(
                    rememberSharedContentState(key = "title:${note.title}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )*/
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "description:${note.description}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = textBoundsTransform,
                    )
                /*.sharedElement(
                    rememberSharedContentState(key = "description:${note.description}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )*/
            )

        }

    }
}

@Preview
@Composable
private fun DiaryNoteItemPreview() {
    DiaryTheme {
        /* DiaryNoteItem(
           note = Note(
               "1",
               "Title for your note",
               "Description for your note")
         ){}*/
    }
}