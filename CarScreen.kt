package com.darren.practicew3_4.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import java.text.NumberFormat
import java.util.Locale

data class Car(
    val id: Int,
    val name: String,
    val transmission: String,
    val year: Int,
    val km: String,
    val fuel: String,
    val image: Int? = null
)

@Composable
fun CarScreen() {
    val orange = Color(0xFFFF6B3D)

    val cars = listOf(
        Car(1, "Tiggo 9 CSH", "Auto", 2024, "5,000 km", "PHEV", R.drawable.tiggo),
        Car(2, "Honda CRV", "Auto", 2013, "15,200 km", "PETROL", R.drawable.crv),
        Car(3, "Toyota Yaris Cross", "Auto", 2024, "30,500 km", "HYBRID", R.drawable.yariscross),
        Car(4, "Daihatsu Xenia", "Manual", 2022, "28,000 km", "PETROL", R.drawable.xenia),
        Car(5, "Toyota Yaris", "Auto", 2021, "52,400 km", "PETROL", R.drawable.yaris),
        Car(6, "Daihatsu Ayla", "Manual", 2020, "41,000 km", "PETROL", R.drawable.ayla),
        Car(7, "Honda HR-V", "Auto", 2022, "22,000 km", "PETROL", R.drawable.hrv),
        Car(8, "Jaecoo J5", "Auto", 2024, "8,000 km", "PETROL", R.drawable.j5),
        Car(9, "MG4", "Auto", 2023, "12,500 km", "EV", R.drawable.mg4),
        Car(10, "Toyota Kijang Innova Reborn", "Auto", 2019, "67,000 km", "DIESEL", R.drawable.reborn)
    )

    //ids favorite car
    var savedIds by remember { mutableStateOf(setOf(1, 3)) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column{
                    Box(
                        modifier = Modifier
                            .size(width = 32.dp, height = 3.dp)
                            .background(orange)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("On Sale", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Text("Find your perfect ride", color = Color.Gray, fontSize = 14.sp)
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(orange.copy(alpha = 0.12f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = orange,
                        modifier = Modifier.size(14.dp)
                    )
                    Text("${savedIds.size} saved", fontSize = 12.sp)
                }
            }
        }

        items(cars, key = { it.id }) { car ->
            val isSaved = car.id in savedIds

            val fuelColor = when (car.fuel) {
                "PHEV" -> Color(0xFF7E57C2)
                "HYBRID" -> Color(0xFF2E9E6B)
                else -> Color(0xFFF59E0B)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // image area
                    Box(
                        modifier = Modifier
                            .size(width = 100.dp, height = 110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray)
                    ) {
                        if (car.image != null) {
                            Image(
                                painter = painterResource(id = car.image),
                                contentDescription = car.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Text("Photo", modifier = Modifier.align(Alignment.Center), fontSize = 12.sp)
                        }

                        //fuel badge
                        Text(
                            text = car.fuel,
                            color = fuelColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(6.dp)
                                .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 2.dp)
                        )
                    }

                    // car info
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(car.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            IconButton(
                                onClick = {
                                    savedIds = if (isSaved) savedIds - car.id else savedIds + car.id
                                }
                            ) {
                                Icon(
                                    imageVector = if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Save",
                                    tint = if (isSaved) orange else Color.Gray
                                )
                            }
                        }

                        Text(
                            text = car.transmission,
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .background(Color(0xFFF1F1F1), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(orange)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("${car.year}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                            Text(car.km, color = Color.Gray, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarScreenPreview() {
    PracticeW34Theme{
        CarScreen()
    }
}