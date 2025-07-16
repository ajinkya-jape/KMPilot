import SwiftUI
import shared

struct ContentView: View {

    @State private var shouldOpenAbout = false

    var body: some View {
        NavigationStack{
            NewsScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle")
                                .labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
                            DeviceInfoScreen()
                        }
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
