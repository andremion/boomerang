import SwiftUI
import Shared

struct WaterCounterView: View {
    
    @ObservedObject var presenter: ObservablePresenter<WaterCounterUiState, WaterCounterUiEvent, WaterCounterUiEffect>
    
    @State private var showAlert = false
    @State private var alertTitle: String = ""
        
    init() {
        presenter = ObservablePresenter(WaterCounterPresenter()) // Inject presenter from any DI framework accordingly
    }
    
    var body: some View {
        if let uiState = presenter.uiState {
            ZStack(alignment: .leading) {
                RoundedRectangle(cornerRadius: 25, style: .continuous)
                    .fill(.bar)

                VStack(alignment: .leading) {

                    Text("Water Counter")
                        .font(.headline)
                    Text("You've had \(uiState.count) glasses of water today")

                    HStack {
                        Button(action: {
                            presenter.onUiEvent(uiEvent: WaterCounterUiEventAddOneClick())
                        }) {
                            Text("Add one")
                        }
                        Button(action: {
                            presenter.onUiEvent(uiEvent: WaterCounterUiEventClearClick())
                        }) {
                            Text("Clear")
                        }
                    }
                    .buttonStyle(.borderedProminent)
                }
                .padding()
            }
            .scaledToFit()
            .onChange(of: alertTitle) {
                showAlert = true
            }
            .alert(
               alertTitle,
               isPresented: $showAlert
            ) {
               Button("OK") {
                   // no-op
               }
            }
            .onAppear {
                presenter.onUiEvent(uiEvent: WaterCounterUiEventInit())
            }
            .onReceive(presenter.$uiEffect) { uiEffect in
                switch uiEffect {
                    case is WaterCounterUiEffectShowCongratulations:
                        alertTitle = "Congratulations! You've taken \(uiState.count) glasses of water today!"
                        
                    default:
                        break
                }
            }
        } else {
            EmptyView()
        }
    }
}

#Preview {
    WaterCounterView()
}
