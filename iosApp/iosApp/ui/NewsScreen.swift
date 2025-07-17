//
//  NewsScreen.swift
//  iosApp
//
//  Created by Ajinkya Jape on 16/07/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

extension NewsScreen{
    
    @MainActor
    class NewsWrapper: ObservableObject{
        
        let newsViewModel: NewsViewModel
        private var job: Kotlinx_coroutines_coreJob?
        
        @Published var newsState: NewsState
            //NewsInjector().newsViewModel
        
        init() {
            newsViewModel = NewsInjector().newsViewModel
            newsState = newsViewModel.newsState.value as! NewsState
        }
        
        func startObserving(){
            job = newsViewModel.observeNewsState { [weak self] newState in
                DispatchQueue.main.async {
                    self?.newsState = newState
                }
            }
        }
        
        deinit {
            job?.cancel(cause: nil)
        }
    }
}

struct NewsScreen: View {
    
    
    @ObservedObject private(set) var viewModel: NewsWrapper
    
    var body: some View {
        VStack {
            AppTitleBar()
                
            if viewModel.newsState.bLoading {
                Loader()
            }
                
            if let error = viewModel.newsState.sError {
                ErrorMessage(message: error)
            }
                
            if(viewModel.newsState.newsList!.count > 0) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(
                            viewModel.newsState.newsList!,
                            id: \.self
                        ) { news in
                            NewsItemView(newsModel: news)
                        }
                    }
                }
            }
                
        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

struct AppTitleBar : View {
    var body: some View {
        Text("News")
            .font(.title)
            .fontWeight(.bold)
    }
}

struct NewsItemView : View{
    var newsModel: NewsModel
       
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: newsModel.sNewsImgUrl!)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(newsModel.sNewsTitle)
                .font(.title3)
                .fontWeight(.bold)
               
            Text(newsModel.sNewsDesc!)
               
            Text(newsModel.sNewsDate)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
    
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}
