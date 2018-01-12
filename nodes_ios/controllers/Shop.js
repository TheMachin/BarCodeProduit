'use strict';

var utils = require('../utils/writer.js');
var Shop = require('../service/ShopService');

module.exports.usersProductsIdShopsGET = function usersProductsIdShopsGET (req, res, next) {
  var id = req.swagger.params['id'].value;
  Shop.usersProductsIdShopsGET(id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdShopsIdShopPUT = function usersProductsIdShopsIdShopPUT (req, res, next) {
  var id = req.swagger.params['id'].value;
  var idShop = req.swagger.params['idShop'].value;
  var formPostShop = req.swagger.params['formPostShop'].value;
  Shop.usersProductsIdShopsIdShopPUT(id,idShop,formPostShop)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdShopsPOST = function usersProductsIdShopsPOST (req, res, next) {
  var id = req.swagger.params['id'].value;
  var formPostShop = req.swagger.params['formPostShop'].value;
  Shop.usersProductsIdShopsPOST(id,formPostShop)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
