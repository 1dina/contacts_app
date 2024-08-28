package com.example.contactsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.contactsapp.data.DataSource
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.theme.Black
import com.example.contactsapp.ui.theme.ContactsAppTheme
import com.example.contactsapp.ui.theme.White

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = White,
                            titleContentColor = Black,
                        ), title = {
                            Text(stringResource(id = R.string.app_name))
                        }, actions = {
                            IconButton(onClick = {
                                doIntent(
                                    this@MainActivity,
                                    this@MainActivity.resources.getString(R.string.my_home)
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_home),
                                    contentDescription = "Home"
                                )
                            }
                        })
                    },
                ) { innerPadding ->
                    ContactsList(
                        DataSource().getContactsList(), modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}

@Composable
fun ContactsList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .height(800.dp),
        columns = GridCells.Fixed(3)
    ) {
        items(contacts) {
            ContactItemUI(contact = it)
        }
    }

}

@Composable
fun ContactItemUI(contact: Contact, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        onClick = { doIntent(context, context.resources.getString(contact.contactNumber)) },
        shape = RectangleShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = contact.contactImage),
                contentDescription = stringResource(id = contact.contactName),
                modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = contact.contactName), style = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Medium
                )
            )
            SelectionContainer {
                Text(
                    text = stringResource(id = contact.contactNumber),
                    style = TextStyle(
                        fontSize = 12.sp, fontWeight = FontWeight.Light
                    ),
                )

            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun ScrollContentPreview() {
    ContactsList(DataSource().getContactsList())
}

private fun doIntent(context: Context, phoneNum: String) {
    val phoneNumFormatted = "tel:$phoneNum"
    val phoneNumUri = phoneNumFormatted.toUri()
    val intent = Intent(Intent.ACTION_DIAL, phoneNumUri)
    context.startActivity(intent)
}