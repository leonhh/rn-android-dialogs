package expo.modules.dialogs

import android.app.AlertDialog
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoDialogsModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoDialogs")

    AsyncFunction("showDialog") {
        title: String,
        positiveButtonText: String,
        negativeButtonText: String,
        promise: Promise ->
      val activity = appContext.activityProvider?.currentActivity ?: return@AsyncFunction false

      activity.runOnUiThread {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        if (positiveButtonText.isNotEmpty()) {
          builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            dialog.dismiss()
            promise.resolve(true)
          }
        }

        if (negativeButtonText.isNotEmpty()) {
          builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
            promise.resolve(false)
          }
        }

        builder.create().show()
      }
    }

    AsyncFunction("showSelectionDialog") {
        options: List<String>,
        title: String,
        negativeButtonText: String,
        promise: Promise ->
      val activity = appContext.activityProvider?.currentActivity ?: return@AsyncFunction false

      activity.runOnUiThread {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        builder.setItems(options.toTypedArray()) { _, which -> promise.resolve(which) }

        if (negativeButtonText.isNotEmpty()) {
          builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
            promise.resolve(-1)
          }
        }

        builder.create().show()
      }
    }
  }
}
