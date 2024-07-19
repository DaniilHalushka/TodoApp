package com.daniil.halushka.todoapp.presentation.screens.about

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.presentation.screens.about.handler.Handler
import com.daniil.halushka.todoapp.presentation.screens.about.reader.DivkitReader
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.yandex.div.DivDataTag
import com.yandex.div.core.Div2Context
import com.yandex.div.core.DivConfiguration
import com.yandex.div.core.view2.Div2View
import com.yandex.div.data.DivParsingEnvironment
import com.yandex.div.json.ParsingErrorLogger
import com.yandex.div.picasso.PicassoDivImageLoader
import com.yandex.div2.DivData
import org.json.JSONObject

@Composable
fun AboutScreen(
    navigationController: NavController
) {
        val context = LocalContext.current
        val divData = DivkitReader(context).readJSON("about.json").asDiv2Data()

        val div2Context = Div2Context(
            baseContext = ContextThemeWrapper(context, 0),
            configuration = createDivConfiguration(context, navigationController),
            lifecycleOwner = LocalLifecycleOwner.current
        )

        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.backPrimaryColor),
            factory = { Div2View(div2Context) },
            update = { view -> view.setData(divData, DivDataTag("div2")) },
            onRelease = { view -> view.cleanup() }
        )
}


private fun JSONObject.asDiv2Data(): DivData {
    val card = getJSONObject("card")

    val environment = DivParsingEnvironment(ParsingErrorLogger.LOG)

    return DivData(environment, card)
}

private fun createDivConfiguration(
    context: Context,
    navigationController: NavController
): DivConfiguration {
    return DivConfiguration
        .Builder(PicassoDivImageLoader(context))
        .actionHandler(Handler(navigationController))
        .build()
}