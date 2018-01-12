'use strict';

var utils = require('../utils/writer.js');
var Document = require('../service/DocumentService');

module.exports.usersProductsIdDocumentsGET = function usersProductsIdDocumentsGET (req, res, next) {
  var id = req.swagger.params['id'].value;
  Document.usersProductsIdDocumentsGET(id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDocumentsIdDocDELETE = function usersProductsIdDocumentsIdDocDELETE (req, res, next) {
  var id = req.swagger.params['id'].value;
  var idDoc = req.swagger.params['idDoc'].value;
  Document.usersProductsIdDocumentsIdDocDELETE(id,idDoc)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDocumentsIdDocGET = function usersProductsIdDocumentsIdDocGET (req, res, next) {
  var id = req.swagger.params['id'].value;
  var idDoc = req.swagger.params['idDoc'].value;
  Document.usersProductsIdDocumentsIdDocGET(id,idDoc)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDocumentsIdDocPUT = function usersProductsIdDocumentsIdDocPUT (req, res, next) {
  var id = req.swagger.params['id'].value;
  var idDoc = req.swagger.params['idDoc'].value;
  var file = req.swagger.params['file'].value;
  var fileName = req.swagger.params['fileName'].value;
  Document.usersProductsIdDocumentsIdDocPUT(id,idDoc,file,fileName)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDocumentsPOST = function usersProductsIdDocumentsPOST (req, res, next) {
  var id = req.swagger.params['id'].value;
  var file = req.swagger.params['file'].value;
  var fileName = req.swagger.params['fileName'].value;
  Document.usersProductsIdDocumentsPOST(id,file,fileName)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDocumentsidDocDownloadGET = function usersProductsIdDocumentsidDocDownloadGET (req, res, next) {
  var id = req.swagger.params['id'].value;
  var idDoc = req.swagger.params['idDoc'].value;
  Document.usersProductsIdDocumentsidDocDownloadGET(id,idDoc)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
