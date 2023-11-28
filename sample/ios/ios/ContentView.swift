//
//  ContentView.swift
//  ios
//
//  Created by André Rêgo on 27/11/2023.
//

import SwiftUI

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
