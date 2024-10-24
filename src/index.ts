import {
  AlertDialogOptions,
  AlertDialogResult,
  RadioButtonDialogOptions,
  RadioButtonDialogResult,
  SelectionDialogOptions,
  SelectionDialogResult,
} from "./ExpoDialogs.types";
import ExpoDialogsModule from "./ExpoDialogsModule";

export async function showDialog(
  options: AlertDialogOptions,
): Promise<AlertDialogResult> {
  return await ExpoDialogsModule.showDialog(options);
}

export async function showSelectionDialog(
  options: SelectionDialogOptions,
): Promise<SelectionDialogResult> {
  return await ExpoDialogsModule.showSelectionDialog(options);
}

export async function showRadioButtonDialog(
  options: RadioButtonDialogOptions,
): Promise<RadioButtonDialogResult> {
  return await ExpoDialogsModule.showRadioButtonDialog(options);
}
