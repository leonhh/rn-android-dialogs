import ExpoDialogsModule from "./ExpoDialogsModule";

export async function showDialog(value: string) {
  return await ExpoDialogsModule.showDialog(value);
}
