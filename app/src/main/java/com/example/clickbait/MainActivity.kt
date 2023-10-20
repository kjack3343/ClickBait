package com.example.clickbait

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clickbait.data.DataSource
import com.example.clickbait.model.Bait
import com.example.clickbait.ui.theme.ClickBaitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickBaitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClickBaitApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickBaitApp(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) {
        val baits = DataSource.baits
        BaitsList(baits= baits, contentPadding = it)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.TitleTH),
                style = MaterialTheme.typography.displaySmall,
            )
        },
        modifier = modifier
    )
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaitsList(
    baits: List<Bait>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(baits) { index, bait ->
                BaitListItem(
                    bait = bait,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered
                            )
                        )
                )
            }
        }
    }
}
@Composable
fun BaitListItem(
    bait: Bait,
    modifier: Modifier = Modifier,
    mediumShape: CutCornerShape = CutCornerShape(16.dp),
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = mediumShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.clickable {
            isExpanded = !isExpanded // Toggle expanded status on click
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(bait.imageRes),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
                }
            }
            Spacer(Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .sizeIn(minHeight = 12.dp)

            ) {
                Text(
                    text = stringResource(bait.nameRes),
                    modifier = Modifier.padding(top = 8.dp)
                )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .sizeIn(minHeight = 12.dp)
            ) {
                Text(
                    text = stringResource(bait.descriptionRes),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            }
        }
    }

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    val bait = Bait(
        R.drawable.travel,
        R.string.name1,
        R.string.description1,
    )
    ClickBaitTheme {
        BaitListItem(bait = bait)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickBaitTheme {
        ClickBaitApp()
    }
}