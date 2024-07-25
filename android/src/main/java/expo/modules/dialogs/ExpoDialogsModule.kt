package expo.modules.dialogs

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.kotlin.Promise
import android.app.AlertDialog
import android.content.Context
import android.app.Activity
import expo.modules.kotlin.exception.Exceptions


class ExpoDialogsModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoDialogs")

    AsyncFunction("showDialog") { message: String, promise: Promise ->
      val builder: AlertDialog.Builder = AlertDialog.Builder(appContext.currentActivity)

      builder
          .setTitle(message)
          .setPositiveButton("Positive") { dialog, which ->
              // Do something.
          }
          .setNegativeButton("Negative") { dialog, which ->
              // Do something else.
          }
          .setItems(arrayOf("Item One", "Item Two", "Item Three")) { dialog, which ->
              // Do something on item tapped.
          }

      val dialog: AlertDialog = builder.create()
      dialog.show()

      promise.resolve(message)
    }
  }
}