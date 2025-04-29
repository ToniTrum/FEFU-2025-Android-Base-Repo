package co.feip.fefu2025.presentation.components.search_bar_component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    viewModel: SearchBarViewModel = hiltViewModel()
) {
    val query by viewModel.searchQuery.collectAsState()

    Box(
        modifier = modifier
            .border(1.dp, color = Color(0xFF575757))
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = viewModel::onSearchQueryChanged,
            placeholder = {
                Text(
                    text = stringResource(R.string.search)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFFFFF))
                .padding(15.dp),
            shape = RoundedCornerShape(50),
            textStyle = TextStyle(fontSize = 20.sp)
        )
    }
}