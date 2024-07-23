package com.daniil.halushka.todoapp.presentation.screens.about.handler

import android.net.Uri
import androidx.navigation.NavController
import com.yandex.div.core.DivActionHandler
import com.yandex.div.core.DivViewFacade
import com.yandex.div.json.expressions.ExpressionResolver
import com.yandex.div2.DivAction

class Handler (
    private val navigationController: NavController
): DivActionHandler() {
    override fun handleAction(
        action: DivAction,
        view: DivViewFacade,
        resolver: ExpressionResolver
    ): Boolean {
        val url = action.url?.evaluate(resolver) ?: return super.handleAction(action, view, resolver)


        return if (url.scheme == SCHEME_NAVIGATION && handleMyAction(url)) {
            true
        } else {
            super.handleAction(action, view, resolver)
        }
    }

    private fun handleMyAction(action: Uri): Boolean {
        return when (action.host) {
            "navigate" -> {
                navigationController.popBackStack()
                true
            }
            else -> false
        }
    }

    companion object {
        const val SCHEME_NAVIGATION = "navigation-action"
    }
}