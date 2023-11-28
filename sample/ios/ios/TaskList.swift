//
//  TaskList.swift
//  ios
//
//  Created by André Rêgo on 28/11/2023.
//

import SwiftUI

struct TaskListView: View {
    @State var checkState: Bool = false
    var body: some View {
        VStack(alignment: .leading) {
            Text("Daily Tasks")
                .font(.headline)
            List {
                HStack {
                    Text("Task #0")
                    Toggle("", isOn: $checkState)
                        .toggleStyle(.switch)
                    Image(systemName: "xmark")
                        .imageScale(.large)
                        .foregroundStyle(.tint)

                }.listRowInsets(EdgeInsets())
                HStack {
                    Text("Task #1")
                    Toggle("", isOn: $checkState)
                        .toggleStyle(.switch)
                    Image(systemName: "xmark")
                        .imageScale(.large)
                        .foregroundStyle(.tint)

                }.listRowInsets(EdgeInsets())
            }
            .listStyle(InsetListStyle())
        }
    }
}

#Preview {
    TaskListView()
}
