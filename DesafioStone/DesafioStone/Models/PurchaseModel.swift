//
//  PurchaseModel.swift
//  DesafioStone
//
//  Created by Luan Barbalho Kalume on 28/09/17.
//  Copyright © 2017 Luan. All rights reserved.
//

import Foundation

struct PurchaseModel {
  
  let card_number: String
  let value: Int
  let cvv: Int
  let card_holder_name: String
  let exp_date: String
  
  init(_ cardNumber: String, _ value: String, _ cvv: String, _ cardHolderName: String, _ expDate: String) {
    self.card_number = cardNumber
    if let cvvInt = Int(cvv) {
      self.cvv = cvvInt
    } else {
      self.cvv = 0
    }
    self.card_holder_name = cardHolderName
    self.exp_date = expDate
    
    let formatedValue = value.replacingOccurrences(of: " ", with: "")
                              .replacingOccurrences(of: "R$", with: "")
                              .replacingOccurrences(of: ",", with: "")
                              .replacingOccurrences(of: ".", with: "")
    if let valueInt = Int(formatedValue) {
      self.value = valueInt
    } else {
      self.value = 0
    }
  }
}
