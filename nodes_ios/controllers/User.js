'use strict';

var utils = require('../utils/writer.js');
var User = require('../service/UserService');

module.exports.getUserByName = function getUserByName (req, res, next) {
  var username = req.swagger.params['username'].value;
  User.getUserByName(username)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersPOST = function usersPOST (req, res, next) {
  var email = req.swagger.params['email'].value;
  var password = req.swagger.params['password'].value;
  User.usersPOST(email,password)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersPUT = function usersPUT (req, res, next) {
  var email = req.swagger.params['email'].value;
  var password = req.swagger.params['password'].value;
  User.usersPUT(email,password)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsGET = function usersProductsGET (req, res, next) {
  User.usersProductsGET()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdDELETE = function usersProductsIdDELETE (req, res, next) {
  var id = req.swagger.params['id'].value;
  User.usersProductsIdDELETE(id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdGET = function usersProductsIdGET (req, res, next) {
  var id = req.swagger.params['id'].value;
  User.usersProductsIdGET(id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsIdPUT = function usersProductsIdPUT (req, res, next) {
  var id = req.swagger.params['id'].value;
  var formPutProductUser = req.swagger.params['formPutProductUser'].value;
  User.usersProductsIdPUT(id,formPutProductUser)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.usersProductsPOST = function usersProductsPOST (req, res, next) {
  var formPutProductUser = req.swagger.params['formPutProductUser'].value;
  User.usersProductsPOST(formPutProductUser)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
