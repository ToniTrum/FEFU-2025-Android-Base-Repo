package views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.Repository
import views.commonComponents.RepositoryCardComponent

@Composable
fun MyStarsSection(repositoryList: List<Repository>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "My Stars",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(repositoryList.take(10)) { repository ->
                    RepositoryCardComponent(repository)
                }
            }
        }

        item {
            Text(
                text = "All Projects",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }

        items(repositoryList) { repository ->
            RepositoryCardComponent(repository)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyStarsSection() {
    val repositoryList = mutableListOf<Repository>()
    for(i in 1..20){
        repositoryList.add(
            Repository(
                _name = "$i Name",
                description = "Description $i",
                _starCount = i,
                _forkCount = i
            )
        )
    }
    MyStarsSection(repositoryList)
}