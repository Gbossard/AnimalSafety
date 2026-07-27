package com.animals.safety.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
  modifier: Modifier = Modifier,
  animal: Animal,
  onBackClick: () -> Unit,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.details_fragment_label))
        },
        navigationIcon = {
          IconButton(onClick = {
            onBackClick()
          }) {
            Icon(
              painter = painterResource(R.drawable.ic_arrow_back_24dp),
              contentDescription = stringResource(id = R.string.contentDescription_go_back)
            )
          }
        }
      )
    },
  ) { contentPadding ->
    AnimalDetails(
      modifier = modifier.padding(contentPadding),
      animal = animal,
    )
  }
}

@Composable
private fun AnimalDetails(
  modifier: Modifier = Modifier,
  animal: Animal,
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
  ) {
    Box(
      modifier = Modifier,
      contentAlignment = Alignment.BottomStart,
    ) {
      Image(
        painter = painterResource(animal.breed.cover),
        contentDescription = stringResource(animal.breed.translatedName),
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(4/3f),
        contentScale = ContentScale.Crop,
      )
      Text(
        text = animal.name,
        color = Color.White,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge
      )
    }
    
    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
      InformationItem(
        painterResource = R.drawable.ic_age,
        painterDescription = R.string.hint_age,
        textResource = R.string.value_age,
        argsText = animal.age
      )
      InformationItem(
        painterResource = R.drawable.ic_weight,
        painterDescription = R.string.hint_weight,
        textResource = R.string.value_weight,
        argsText = animal.weight
      )
    }

    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
      InformationItem(
        painterResource = R.drawable.ic_height,
        painterDescription = R.string.hint_height,
        textResource = R.string.value_height,
        argsText = animal.height
      )
    }
  }
}

@Composable
private fun InformationItem(
  modifier: Modifier = Modifier,
  painterResource: Int,
  painterDescription: Int,
  textResource: Int,
  argsText: Any
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.padding(horizontal = 16.dp)
  ) {
    Image(
      painter = painterResource(painterResource),
      contentDescription = stringResource(painterDescription),
      modifier = Modifier
        .size(90.dp)
        .padding(16.dp),
      colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
    )
    Text(
      text = stringResource(textResource, argsText),
      style = MaterialTheme.typography.bodyMedium
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun AnimalDetailsPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    AnimalDetails(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
    )
  }
}