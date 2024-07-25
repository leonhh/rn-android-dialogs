import * as ExpoDialogs from "expo-dialogs";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={async () => {
          const result = await ExpoDialogs.showDialog({
            title: "Hello World",
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
            title: "Hello World",
            negativeButtonText: "Cancel",
          });

          console.log("Selected index:", result);
        }}
      >
        <Text>Show selection dialog</Text>
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
