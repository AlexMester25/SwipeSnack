
 <h1 align="center">◀ SwipeSnack ▶</h1>

<p align="center">
  <a href="https://jitpack.io/#AlexMester25/SwipeSnack">
    <img src="https://jitpack.io/v/AlexMester25/SwipeSnack.svg" />
  </a>
  <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
  <img src="https://img.shields.io/badge/Kotlin-1.9.24-blue.svg" />
  <img src="https://img.shields.io/badge/Compose-1.5.3-blue.svg" />
</p>

**SwipeSnack** - is a lightweight Jetpack Compose library for custom Snackbars with support for:<br>

✔ Swipe to hide<br>
✔ Icon, text and actions customization<br>
✔ Auto-disappear on timer<br>
✔ Convenient API via `SnackSwipeBox`.<br>
#

 <p align="center">
  <img src="https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExbDFmYnU0YnRyZTNtandqemt3cHczYXlyaWl4OGVmaHBkZ2FoYTF0ZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/f9Ti2yEFGCumoXJLhq/giphy.gif"/>
</p> 

## 1. Connecting a repository

The library is available via JitPack.
Add the JitPack repository to your `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```
 Add the dependency in `build.gradle.kts`
```kotlin
dependencies {
    implementation 'com.github.senseiiii12:SwipeSnack:0.2.1'
}
```
## 2. Usage
### Wrap the screen in `SnackSwipeBox`

SnackSwipeBox creates a SnackSwipeController and places a host to display the snackbar.
The controller is passed into the content, and messages can be displayed through it.
```kotlin
SnackSwipeBox { snackSwipeController ->
    // you any content
    Column( 
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                snackSwipeController.showSnackSwipe(  //show SnackSwipe
                    messageText = {
                        Text(
                            text = "It`s custom SnackSwipe",
                            color = Color.White
                        )
                    },
                    dismissAction = {
                        IconButton(onClick = { snackSwipeController.close() }) {  //close action SnackSwipe
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) {
            Text("Show SnackSwipe")
        }
    }
    // you any content
}
```
### `SnackSwipeController`
Methods for managing the snackbar:
```kotlin
fun SnackSwipeController.showSnackSwipe(
    messageText: @Composable () -> Unit,
    icon: (@Composable () -> Unit)? = null,
    customAction: (@Composable () -> Unit)? = null,
    dismissAction: (@Composable (() -> Unit))? = null,
    backgroundColor: Color = Color.Black,
    durationMillis: Long = 3000,
    shape: Shape = RoundedCornerShape(12.dp),
    elevation: Dp = 6.dp,
    innerPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
    outerPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
) 

fun close()
```

