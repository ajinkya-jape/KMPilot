//
//  DeviceInfoScreen.swift
//  iosApp
//
//  Created by Ajinkya Jape on 15/07/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DeviceInfoScreen: View {
    
    // Row Item View
    private struct RowItem: Hashable{
        let sTitle: String
        let sSubTitle: String
    }
    

    private let items: [RowItem] = {
        let platform = Platform()
        platform.getDeviceInfo()
        
        
        //set or init Data
        var resultData:[RowItem] = [
            .init(sTitle: "OS Name",sSubTitle: platform.osName),
            .init(sTitle: "OS Version",sSubTitle : platform.osVersion),
            .init(sTitle: "Device Model",sSubTitle: platform.deviceModel),
            .init(sTitle: "Density",sSubTitle: "@\(platform.density)x"),
          ]
        return resultData
       
    }()
    
    
    
    
    var body: some View {
        Text("About Device")
            .font(.headline)
            .fontWeight(.bold)
        
        List{
            ForEach(items, id:\.self){ items in
                VStack(alignment: .leading){
                    
                    Text(items.sTitle)
                        .font(.footnote)
                        .foregroundStyle(.primary)
                        .fontWeight(.bold)
                    
                    Text(items.sSubTitle)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                }
                
            }
        }
    }
}

#Preview {
    DeviceInfoScreen()
}
