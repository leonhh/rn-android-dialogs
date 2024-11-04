package expo.modules.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.widget.TextView
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.lang.ref.WeakReference

class ExpoDialogsModule : Module() {
    private var currentDialog: WeakReference<AlertDialog>? = null

    private fun isActivityValid(activity: Activity?): Boolean {
        return activity != null && !activity.isFinishing && !activity.isDestroyed
    }

    private fun dismissCurrentDialog() {
        try {
            currentDialog?.get()?.dismiss()
            currentDialog = null
        } catch (_: Exception) { }
    }

    override fun definition() = ModuleDefinition {
        Name("ExpoDialogs")

        OnDestroy {
            dismissCurrentDialog()
        }

        Function("dismissDialog") {
            dismissCurrentDialog()
        }

        AsyncFunction("showDialog") { 
            arguments: Map<String, Any?>,
            promise: Promise ->
            val activity = appContext.activityProvider?.currentActivity
            
            if (!isActivityValid(activity)) {
                promise.resolve(false)
                return@AsyncFunction
            }

            activity?.let { safeActivity ->
                try {
                    safeActivity.runOnUiThread {
                        try {
                            dismissCurrentDialog()

                            val builder = AlertDialog.Builder(safeActivity)
                            builder.setTitle(arguments["title"] as? String ?: "")
                            (arguments["message"] as? String)?.let { builder.setMessage(it) }
                            
                            val positiveButtonText = arguments["positiveButtonText"] as? String ?: ""
                            if (positiveButtonText.isNotEmpty()) {
                                builder.setPositiveButton(positiveButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(true)
                                }
                            }
                            
                            val negativeButtonText = arguments["negativeButtonText"] as? String ?: ""
                            if (negativeButtonText.isNotEmpty()) {
                                builder.setNegativeButton(negativeButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(false)
                                }
                            }

                            val dialog = builder.create()
                            dialog.setCancelable(true)
                            dialog.setOnCancelListener {
                                currentDialog = null
                                promise.resolve(false)
                            }
                            
                            dialog.setOnDismissListener {
                                currentDialog = null
                            }
                            
                            currentDialog = WeakReference(dialog)
                            dialog.show()
                        } catch (_: Exception) {
                            promise.resolve(false)
                        }
                    }
                } catch (_: Exception) {
                    promise.resolve(false)
                }
            } ?: promise.resolve(false)
        }

        AsyncFunction("showSelectionDialog") { 
            arguments: Map<String, Any?>,
            promise: Promise ->
            val activity = appContext.activityProvider?.currentActivity
            
            if (!isActivityValid(activity)) {
                promise.resolve(-1)
                return@AsyncFunction
            }

            activity?.let { safeActivity ->
                try {
                    safeActivity.runOnUiThread {
                        try {
                            dismissCurrentDialog()

                            val builder = AlertDialog.Builder(safeActivity)
                            builder.setTitle(arguments["title"] as? String ?: "")
                            
                            val options = (arguments["options"] as? List<*>)?.filterIsInstance<String>() ?: listOf()
                            builder.setItems(options.toTypedArray()) { dialog, which ->
                                dialog.dismiss()
                                currentDialog = null
                                promise.resolve(which)
                            }
                            
                            val negativeButtonText = arguments["negativeButtonText"] as? String ?: ""
                            if (negativeButtonText.isNotEmpty()) {
                                builder.setNegativeButton(negativeButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(-1)
                                }
                            }

                            val dialog = builder.create()
                            dialog.setCancelable(true)
                            dialog.setOnCancelListener {
                                currentDialog = null
                                promise.resolve(-1)
                            }
                            
                            dialog.setOnDismissListener {
                                currentDialog = null
                            }
                            
                            currentDialog = WeakReference(dialog)
                            dialog.show()
                        } catch (_: Exception) {
                            promise.resolve(-1)
                        }
                    }
                } catch (_: Exception) {
                    promise.resolve(-1)
                }
            } ?: promise.resolve(-1)
        }

        AsyncFunction("showRadioButtonDialog") { 
            arguments: Map<String, Any?>,
            promise: Promise ->
            val activity = appContext.activityProvider?.currentActivity
            
            if (!isActivityValid(activity)) {
                promise.resolve(-1)
                return@AsyncFunction
            }

            activity?.let { safeActivity ->
                try {
                    safeActivity.runOnUiThread {
                        try {
                            dismissCurrentDialog()
                            
                            var checkedItem = (arguments["selectedIndex"] as? Number)?.toInt() ?: 0
                            
                            val builder = AlertDialog.Builder(safeActivity)
                            builder.setTitle(arguments["title"] as? String ?: "")
                            
                            val options = (arguments["options"] as? List<*>)?.filterIsInstance<String>() ?: listOf()
                            builder.setSingleChoiceItems(
                                options.toTypedArray(),
                                checkedItem
                            ) { _, which ->
                                checkedItem = which
                            }
                            
                            val positiveButtonText = arguments["positiveButtonText"] as? String ?: "OK"
                            if (positiveButtonText.isNotEmpty()) {
                                builder.setPositiveButton(positiveButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(checkedItem)
                                }
                            }
                            
                            val negativeButtonText = arguments["negativeButtonText"] as? String ?: ""
                            if (negativeButtonText.isNotEmpty()) {
                                builder.setNegativeButton(negativeButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(-1)
                                }
                            }

                            val dialog = builder.create()
                            dialog.setCancelable(true)
                            dialog.setOnCancelListener {
                                currentDialog = null
                                promise.resolve(-1)
                            }
                            
                            dialog.setOnDismissListener {
                                currentDialog = null
                            }
                            
                            currentDialog = WeakReference(dialog)
                            dialog.show()
                        } catch (_: Exception) {
                            promise.resolve(-1)
                        }
                    }
                } catch (_: Exception) {
                    promise.resolve(-1)
                }
            } ?: promise.resolve(-1)
        }

        AsyncFunction("showCheckboxDialog") { 
            arguments: Map<String, Any?>,
            promise: Promise ->
            val activity = appContext.activityProvider?.currentActivity
            
            if (!isActivityValid(activity)) {
                promise.resolve(null)
                return@AsyncFunction
            }

            activity?.let { safeActivity ->
                try {
                    safeActivity.runOnUiThread {
                        try {
                            dismissCurrentDialog()
                            
                            val options = (arguments["options"] as? List<*>)?.filterIsInstance<String>() ?: listOf()
                            val initialSelection = (arguments["selectedIndices"] as? List<*>)?.filterIsInstance<Number>()?.map { it.toInt() } ?: listOf()
                            
                            val checkedItems = BooleanArray(options.size) { index ->
                                initialSelection.contains(index)
                            }
                            
                            val builder = AlertDialog.Builder(safeActivity)
                            builder.setTitle(arguments["title"] as? String ?: "")
                            
                            builder.setMultiChoiceItems(
                                options.toTypedArray(),
                                checkedItems
                            ) { _, which, isChecked ->
                                checkedItems[which] = isChecked
                            }
                            
                            val positiveButtonText = arguments["positiveButtonText"] as? String ?: "OK"
                            if (positiveButtonText.isNotEmpty()) {
                                builder.setPositiveButton(positiveButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    // Convert checked items to list of indices
                                    val selectedIndices = checkedItems.mapIndexed { index, checked ->
                                        if (checked) index else null
                                    }.filterNotNull()
                                    promise.resolve(selectedIndices)
                                }
                            }
                            
                            val negativeButtonText = arguments["negativeButtonText"] as? String ?: ""
                            if (negativeButtonText.isNotEmpty()) {
                                builder.setNegativeButton(negativeButtonText) { dialog, _ ->
                                    dialog.dismiss()
                                    currentDialog = null
                                    promise.resolve(null)
                                }
                            }

                            val dialog = builder.create()
                            dialog.setCancelable(true)
                            dialog.setOnCancelListener {
                                currentDialog = null
                                promise.resolve(null)
                            }
                            
                            dialog.setOnDismissListener {
                                currentDialog = null
                            }
                            
                            currentDialog = WeakReference(dialog)
                            dialog.show()
                        } catch (_: Exception) {
                            promise.resolve(null)
                        }
                    }
                } catch (_: Exception) {
                    promise.resolve(null)
                }
            } ?: promise.resolve(null)
        }
    }
}