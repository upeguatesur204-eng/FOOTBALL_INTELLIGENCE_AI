package com.footballintelligence.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF080D18)
private val Panel = Color(0xFF121B2B)
private val Panel2 = Color(0xFF19263A)
private val Accent = Color(0xFF36D6A0)
private val Muted = Color(0xFF91A0B7)
private val White = Color(0xFFF4F7FB)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FootballApp() }
    }
}

@Composable
fun FootballApp() {
    var home by remember { mutableStateOf("Real Madrid") }
    var away by remember { mutableStateOf("Barcelona") }
    var analyzed by remember { mutableStateOf(false) }
    var tab by remember { mutableStateOf("Analizar") }
    val teams = listOf("Real Madrid", "Barcelona", "Atlético de Madrid", "Manchester City", "Liverpool", "Arsenal", "Bayern Múnich", "PSG", "Guatemala", "Argentina", "Brasil", "México")

    MaterialTheme(colorScheme = darkColorScheme(
        background = Bg, surface = Panel, primary = Accent,
        onPrimary = Bg, onBackground = White, onSurface = White,
        secondary = Color(0xFF66A7FF)
    )) {
        Scaffold(containerColor = Bg, bottomBar = {
            NavigationBar(containerColor = Panel) {
                listOf("Analizar", "Jugadores", "Historial").forEach { item ->
                    NavigationBarItem(
                        selected = tab == item,
                        onClick = { tab = item },
                        icon = {
                            Icon(
                                imageVector = when(item) {
                                    "Jugadores" -> Icons.Default.Groups
                                    "Historial" -> Icons.Default.History
                                    else -> Icons.Default.Analytics
                                }, contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Accent, selectedTextColor = Accent,
                            indicatorColor = Panel2, unselectedTextColor = Muted
                        )
                    )
                }
            }
        }) { padding ->
            Column(
                Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
                    .background(Bg).padding(horizontal = 18.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text("FOOTBALL", color = White, fontSize = 21.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                        Text("INTELLIGENCE AI", color = Accent, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    }
                    Surface(color = Panel2, shape = RoundedCornerShape(50)) {
                        Text("●  MODO DEMO", color = Accent, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 11.dp, vertical = 8.dp))
                    }
                }

                if (tab == "Analizar") {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text("CENTRO DE ANÁLISIS", color = Muted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
                        Text("Compara. Analiza. Predice.", color = White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text("Selecciona dos equipos para preparar el análisis del enfrentamiento.", color = Muted, fontSize = 13.sp)
                    }
                    CardBox {
                        Text("SELECCIONAR ENFRENTAMIENTO", color = Muted, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        TeamPicker("LOCAL", home, teams) { home = it; analyzed = false }
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text("VS", color = Accent, fontSize = 20.sp, fontWeight = FontWeight.Black)
                        }
                        TeamPicker("VISITANTE", away, teams) { away = it; analyzed = false }
                        Button(
                            onClick = { analyzed = home != away },
                            enabled = home != away,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Accent, contentColor = Bg),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Default.Analytics, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("ANALIZAR PARTIDO", fontWeight = FontWeight.ExtraBold)
                        }
                        Text("La conexión con fuentes deportivas se habilitará en la siguiente etapa. No se generan pronósticos reales en este modo.", color = Muted, fontSize = 10.sp)
                    }
                    if (analyzed) {
                        CardBox {
                            Text("ENFRENTAMIENTO SELECCIONADO", color = Accent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text("$home  vs  $away", color = White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            Text("Listo para conectar datos reales: forma reciente, goles, xG, plantilla, lesiones y sanciones.", color = Muted, fontSize = 12.sp)
                            InfoLine("Estado", "Pendiente de datos en vivo")
                            InfoLine("Predicción", "No disponible en modo demo")
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(Modifier.weight(1f)) { MetricTile("12+", "Ligas previstas") }
                        Box(Modifier.weight(1f)) { MetricTile("11", "Métricas clave") }
                        Box(Modifier.weight(1f)) { MetricTile("24/7", "Consulta online") }
                    }
                    CardBox {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.HealthAndSafety, contentDescription = null, tint = Accent)
                            Spacer(Modifier.width(8.dp))
                            Text("CONTROL DE INFORMACIÓN", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Text("Las lesiones y suspensiones deberán mostrar fuente, fecha y nivel de confirmación. Los datos no verificados se identificarán como tales.", color = Muted, fontSize = 12.sp)
                    }
                } else if (tab == "Jugadores") {
                    SectionHeader("CENTRO DE JUGADORES", "Estadísticas individuales y disponibilidad")
                    CardBox {
                        Icon(Icons.Default.Groups, contentDescription = null, tint = Accent)
                        Text("Módulo de jugadores", color = White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Se conectará a las plantillas y estadísticas de las fuentes gratuitas. Mostrará partidos, titularidades, minutos, goles, asistencias y estado físico cuando exista información verificable.", color = Muted, fontSize = 13.sp)
                    }
                } else {
                    SectionHeader("HISTORIAL", "Tus análisis guardados")
                    CardBox {
                        Icon(Icons.Default.History, contentDescription = null, tint = Accent)
                        Text("Todavía no hay análisis guardados", color = White, fontWeight = FontWeight.Bold)
                        Text("En la siguiente versión se guardarán los pronósticos y se compararán con los resultados finales para medir su precisión.", color = Muted, fontSize = 13.sp)
                    }
                }
                Text("FOOTBALL INTELLIGENCE AI  •  Versión 0.1.0", color = Muted, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun CardBox(content: @Composable ColumnScope.() -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(13.dp), content = content)
    }
}

@Composable
fun TeamPicker(label: String, selected: String, teams: List<String>, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, color = Muted, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Box {
            Surface(
                color = Panel2, shape = RoundedCornerShape(13.dp),
                modifier = Modifier.fillMaxWidth().clickable { expanded = true }
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(selected, color = White, fontWeight = FontWeight.SemiBold)
                    Text("⌄", color = Accent, fontSize = 20.sp)
                }
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.heightIn(max = 320.dp).background(Panel)) {
                teams.forEach { team ->
                    DropdownMenuItem(text = { Text(team, color = White) }, onClick = { onSelected(team); expanded = false })
                }
            }
        }
    }
}

@Composable
fun MetricTile(value: String, label: String) {
    Surface(color = Panel, shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, color = Accent, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            Text(label, color = Muted, fontSize = 9.sp)
        }
    }
}

@Composable
fun InfoLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Muted, fontSize = 12.sp)
        Text(value, color = White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(title, color = Accent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.3.sp)
        Text(subtitle, color = White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
    }
}
