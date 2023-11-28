//
//  WaterCounter.swift
//  ios
//
//  Created by André Rêgo on 28/11/2023.
//

import SwiftUI

struct WaterCounterView: View {
    var body: some View {
        ZStack(alignment: .leading) {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(.bar)

            VStack(alignment: .leading) {
                Text("Water Counter")
                    .font(.headline)
                Text("You've had 0 glasses of water today")
                HStack {
                    Button(action: {}) {
                        Text("Add one")
                    }
                    Button(action: {}) {
                        Text("Clear")
                    }
                }
                .buttonStyle(.borderedProminent)
            }
            .padding()
        }.scaledToFit()
    }
}

#Preview {
    WaterCounterView()
}
