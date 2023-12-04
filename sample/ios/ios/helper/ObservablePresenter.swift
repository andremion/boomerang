import Foundation
import Shared

class ObservablePresenter<UiState, UiEvent, UiEffect>: ObservableObject, LibraryUiEventHandler {

    @Published var uiState: UiState?
    @Published var uiEffect: UiEffect?
    
    private let presenter: LibraryPresenter

    init(_ presenter: LibraryPresenter) {
        self.presenter = presenter
        asPublisher(PresenterExtensionsKt.uiStateFlow(presenter))
            .compactMap { $0 as? UiState }
            .receive(on: DispatchQueue.main)
            .assign(to: &$uiState)
        asPublisher(PresenterExtensionsKt.uiEffectFlow(presenter))
            .compactMap { $0 as? UiEffect }
            .receive(on: DispatchQueue.main)
            .assign(to: &$uiEffect)
    }
 
    func onUiEvent(uiEvent: Any?) {
        presenter.onUiEvent(uiEvent: uiEvent)
    }
}
