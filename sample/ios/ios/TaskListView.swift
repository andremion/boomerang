import SwiftUI
import Shared

struct TaskListView: View {
    
    @ObservedObject var presenter: ObservablePresenter<TaskListUiState, TaskListUiEvent, TaskListUiEffect>

    @State private var bindlableTasks: Array<BindlableTask> = Array()
    @State private var showAlert = false
    @State private var alertTitle: String = ""
    
    init() {
        presenter = ObservablePresenter(TaskListPresenter()) // Inject presenter from any DI framework accordingly
    }

    var body: some View {
        if let uiState = presenter.uiState {
            VStack(alignment: .leading) {

                Text("Daily Tasks")
                    .font(.headline)
                
                if (uiState.tasks.isEmpty) {
                    ProgressView()
                        .frame(maxWidth: .infinity)
                } else {
                    List {
                        ForEach($bindlableTasks, id: \.id) { $task in
                            HStack {
                                Text(task.name)
                                Toggle("", isOn: $task.isChecked)
                                    .toggleStyle(.switch)
                                    .onChange(of: task.isChecked) { oldValue, newValue in
                                        presenter.onUiEvent(uiEvent: TaskListUiEventCheckTaskClick(taskId: task.id, isChecked: newValue))
                                    }
                                Button(action: {
                                    presenter.onUiEvent(uiEvent: TaskListUiEventRemoveTaskClick(taskId: task.id))
                                }) {
                                    Image(systemName: "xmark")
                                        .imageScale(.large)
                                        .foregroundStyle(.tint)
                                }
                                .buttonStyle(BorderlessButtonStyle())
                            }
                            .listRowInsets(EdgeInsets())
                        }
                    }
                    .listStyle(InsetListStyle())
                }
            }
            .frame(
                maxWidth: .infinity,
                maxHeight: .infinity,
                alignment: .topLeading
            )
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
                presenter.onUiEvent(uiEvent: TaskListUiEventInit())
            }
            .onReceive(presenter.$uiState) { uiState in
                bindlableTasks = uiState?.tasks.transform() ?? Array()
            }
            .onReceive(presenter.$uiEffect) { uiEffect in
                switch uiEffect {
                    case is TaskListUiEffectShowCongratulations:
                        alertTitle = "Congratulations! You've done all the tasks!"
                        
                    default:
                        break
                }
            }
        } else {
            EmptyView()
        }
    }
}

private struct BindlableTask {
    let id: Int64
    let name: String
    var isChecked: Bool
}

private extension Array<Task> {
    func transform() -> Array<BindlableTask> {
        return map { BindlableTask(id: $0.id, name: $0.name, isChecked: $0.isDone) }
    }
}

#Preview {
    TaskListView()
}
