import ExpoDialogsModule from "./ExpoDialogsModule";

export async function showDialog({
  title,
  positiveButtonText = "Ok",
  negativeButtonText = "Cancel",
}: {
  title: string;
  positiveButtonText?: string;
  negativeButtonText?: string;
}): Promise<boolean> {
  return await ExpoDialogsModule.showDialog(
    title,
    positiveButtonText,
    negativeButtonText,
  );
}

export async function showSelectionDialog({
  options = [],
  title,
  negativeButtonText = "Canel",
}: {
  options: string[];
  title: string;
  negativeButtonText?: string;
}): Promise<number> {
  return await ExpoDialogsModule.showSelectionDialog(
    options,
    title,
    negativeButtonText,
  );
}
