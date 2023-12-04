import SwiftUI
import Shared

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack {
                WaterCounterView()
                TaskListView()
            }
            .padding()
            .navigationTitle("Boomerang demo")
        }
    }
}

#Preview {
    ContentView()
}
