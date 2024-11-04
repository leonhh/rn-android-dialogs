import * as ExpoDialogs from "expo-dialogs";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={async () => {
          const result = await ExpoDialogs.showDialog({
            title: "Hello World",
            message: "This is a message",
            positiveButtonText: "OK",
            negativeButtonText: "Cancel",
          });

          console.log("Dialog result:", result);
        }}
      >
        <Text>Show dialog</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={async () => {
          const result = await ExpoDialogs.showSelectionDialog({
            options: ["Option 1", "Option 2", "Option 3"],
            // message: "Lorem ipsum!",
            title: "Hello World",
            negativeButtonText: "Cancel",
          });

          console.log("Selected index:", result);
        }}
      >
        <Text>Show selection dialog</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={async () => {
          const result = await ExpoDialogs.showRadioButtonDialog({
            options: ["Option 1", "Option 2", "Option 3"],
            title: "Hello World",
            selectedIndex: 1,
            negativeButtonText: "Cancel",
          });

          console.log("Selected index:", result);
        }}
      >
        <Text>Show radio button dialog</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={async () => {
          const result = await ExpoDialogs.showCheckboxDialog({
            options: ["Option 1", "Option 2", "Option 3"],
            title: "Hello World",
            selectedIndices: [1],
            negativeButtonText: "Cancel",
          });

          console.log(`Selected indices: ${JSON.stringify(result)}`);
        }}
      >
        <Text>Show checkbox dialog</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => {
          ExpoDialogs.showDialog({
            title: "Hello World",
            message: "This is a message",
            positiveButtonText: "OK",
            negativeButtonText: "Cancel",
          });

          // Dismiss after 5 seconds
          setTimeout(() => {
            ExpoDialogs.dismissDialog();
          }, 5000);
        }}
      >
        <Text>Dismiss active dialog</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
