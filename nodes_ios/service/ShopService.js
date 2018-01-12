'use strict';


/**
 * get a location shop of product
 * Get a location shop of product
 *
 * id String 
 * returns Shop
 **/
exports.usersProductsIdShopsGET = function(id) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "address" : "address",
  "city" : "city",
  "name" : "name",
  "online" : true,
  "id" : 1
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * update a new shop for a product
 * Update a new shop for a product
 *
 * id String 
 * idShop String 
 * formPostShop FormPostShop_1  (optional)
 * returns Shop
 **/
exports.usersProductsIdShopsIdShopPUT = function(id,idShop,formPostShop) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "address" : "address",
  "city" : "city",
  "name" : "name",
  "online" : true,
  "id" : 1
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * add or create a new shop for a product
 * Add or create a new shop for a product
 *
 * id String 
 * formPostShop FormPostShop  (optional)
 * returns Shop
 **/
exports.usersProductsIdShopsPOST = function(id,formPostShop) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "address" : "address",
  "city" : "city",
  "name" : "name",
  "online" : true,
  "id" : 1
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

