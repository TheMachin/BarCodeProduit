'use strict';

var utils = require('../utils/writer.js');
var Products = require('../service/ProductsService');

module.exports.addPet = function addPet (req, res, next) {
  var body = req.swagger.params['body'].value;
  Products.addPet(body)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.productsGtinGET = function productsGtinGET (req, res, next) {
  var gtin = req.swagger.params['gtin'].value;
  Products.productsGtinGET(gtin)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.updateProduct = function updateProduct (req, res, next) {
  var gtin = req.swagger.params['gtin'].value;
  var body = req.swagger.params['body'].value;
  Products.updateProduct(gtin,body)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
