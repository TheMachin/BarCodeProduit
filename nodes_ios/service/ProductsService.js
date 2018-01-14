'use strict';


/**
 * Add a new product
 *
 * body Product Product object to save in a db
 * no response value expected for this operation
 **/
exports.addPet = function(body) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * Find product by gtin
 * Find an existing product from db
 *
 * gtin Long ID of pet to update
 * returns List
 **/
exports.productsGtinGET = function(gtin) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "compagny" : {
    "name" : "name"
  },
  "name" : "name",
  "id" : 0
}, {
  "compagny" : {
    "name" : "name"
  },
  "name" : "name",
  "id" : 0
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update an product
 *
 * gtin Long ID of pet to update
 * body Product Pet object that needs to be added to the store
 * returns List
 **/
exports.updateProduct = function(gtin,body) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "compagny" : {
    "name" : "name"
  },
  "name" : "name",
  "id" : 0
}, {
  "compagny" : {
    "name" : "name"
  },
  "name" : "name",
  "id" : 0
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

