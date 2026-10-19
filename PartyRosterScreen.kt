package com.darren.practicew3_4.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darren.practicew3_4.R
import com.darren.practicew3_4.ui.theme.PracticeW34Theme

data class Hero(
    val id: Int,
    val name: String,
    val heroClass: String,
    val rarity: String,
    val level: Int,
    val hp: Int,
    val maxHp: Int,
    val mp: Int,
    val maxMp: Int,
    val cp: Int,
    val image: Int,
    val inSquad: Boolean = false
)

@Composable
fun PartyRosterScreen() {
    val gold = Color(0xFFFFB400)
    val darkBg = Color(0xFF0F1117)
    val cardBg = Color(0xFF181B24)
    val borderGray = Color(0xFF2A2F3A)

    val heroes = listOf(
        Hero(1, "Alistair", "High Templar", "MYTHIC", 50, 1650, 1800, 650, 800, 5600, R.drawable.paladin),
        Hero(2, "Theresa", "Divine Oracle", "MYTHIC", 49, 1100, 1200, 2100, 2400, 5480, R.drawable.cleric),
        Hero(3, "Vespera", "Shadowblade", "MYTHIC", 48, 890, 950, 410, 600, 5350, R.drawable.rogue),
        Hero(4, "Elaria", "Archmage", "MYTHIC", 47, 450, 600, 1850, 2000, 5120, R.drawable.archmage, true),
        Hero(5, "Gorak", "Berserker", "LEGENDARY", 45, 2100, 2400, 200, 300, 4800, R.drawable.berserker, true),
        Hero(6, "Seraphine", "Archmage", "LEGENDARY", 44, 700, 900, 1500, 1800, 4650, R.drawable.archmage),
        Hero(7, "Darius", "Berserker", "EPIC", 40, 1900, 2200, 150, 250, 4100, R.drawable.berserker),
        Hero(8, "Lyra", "Divine Oracle", "EPIC", 38, 800, 1000, 1700, 2000, 3900, R.drawable.cleric)
    )

    val filters = listOf("All (14)", "Berserker (2)", "Archmage (3)", "Paladin (2)", "Rogue (3)", "Cleric (4)")
    var selectedFilter by remember { mutableStateOf("All (14)") }
    var search by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // title and expedition badge
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text("Party Roster", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Text("Guild Vanguard • 14 Heroes Available", color = Color.Gray, fontSize = 13.sp)
                    }
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .border(1.dp, gold, RoundedCornerShape(20.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = gold, modifier = Modifier.size(14.dp))
                        Text("Expedition", color = gold, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }

            //cards
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, gold.copy(alpha = 0.5f)),
                    colors = CardDefaults.cardColors(containerColor = cardBg)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("EXPEDITION SQUAD (3/4)", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf(R.drawable.berserker, R.drawable.archmage, R.drawable.paladin).forEach { img ->
                                    Image(
                                        painter = painterResource(id = img),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .border(1.dp, gold, RoundedCornerShape(8.dp))
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .border(1.dp, borderGray, RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.Gray)
                                }
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("⚔ 14750 CP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = gold, contentColor = Color.Black)
                            ) {
                                Text("Deploy", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            //search
            item {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text("Search by hero name, class, or rarity...", fontSize = 13.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = gold,
                        unfocusedBorderColor = borderGray,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedLeadingIconColor = Color.Gray,
                        focusedLeadingIconColor = gold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //chips filter
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filters) { filter ->
                            val isSelected = filter == selectedFilter
                            Text(
                                text = filter,
                                color = if (isSelected) gold else Color.LightGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (isSelected) gold.copy(alpha = 0.15f) else Color.Transparent)
                                    .border(1.dp, if (isSelected) gold else borderGray, RoundedCornerShape(20.dp))
                                    .clickable { selectedFilter = filter }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Sort: Power",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(cardBg)
                            .border(1.dp, borderGray, RoundedCornerShape(10.dp))
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    )
                }
            }

            //hero cards
            items(heroes, key = { it.id }) { hero ->
                val rarityColor = when (hero.rarity) {
                    "MYTHIC" -> Color(0xFFFF4D5E)
                    "LEGENDARY" -> Color(0xFFFFB400)
                    else -> Color(0xFFAB47BC)
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, if (hero.inSquad) gold else borderGray),
                    colors = CardDefaults.cardColors(containerColor = cardBg)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // badges and power
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = hero.rarity,
                                    color = rarityColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .border(1.dp, rarityColor, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                                Text(
                                    text = hero.heroClass,
                                    color = Color.LightGray,
                                    fontSize = 10.sp,
                                    modifier = Modifier
                                        .background(Color(0xFF2A2F3A), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("⚔ ${hero.cp} CP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                if (hero.inSquad) {
                                    Text(
                                        text = "IN SQUAD",
                                        color = gold,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .border(1.dp, gold, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        //drawable photos and stats
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(1.dp, rarityColor, RoundedCornerShape(10.dp))
                                    .background(Color(0xFF222633))
                            ) {
                                Image(
                                    painter = painterResource(id = hero.image),
                                    contentDescription = hero.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Text(
                                    text = "Lvl ${hero.level}",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(3.dp)
                                        .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(hero.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
                                }

                                // hp bar
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("HP", color = Color.Gray, fontSize = 11.sp, modifier = Modifier.width(26.dp))
                                    LinearProgressIndicator(
                                        progress = { hero.hp / hero.maxHp.toFloat() },
                                        color = Color(0xFFFF4D5E),
                                        trackColor = Color(0xFF2A2F3A),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(6.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                    )
                                }

                                // mp bar
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("MP", color = Color.Gray, fontSize = 11.sp, modifier = Modifier.width(26.dp))
                                    LinearProgressIndicator(
                                        progress = { hero.mp / hero.maxMp.toFloat() },
                                        color = Color(0xFF2196F3),
                                        trackColor = Color(0xFF2A2F3A),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(6.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PartyRosterScreenPreview() {
    PracticeW34Theme {
        PartyRosterScreen()
    }
}