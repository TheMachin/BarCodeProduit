'use strict';


/**
 * Get user by user name
 *
 * username String The name that needs to be fetched. Use user1 for testing.
 * returns User
 **/
exports.getUserByName = function(username) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "username" : "username"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Create user
 * Creating a user.
 *
 * email String Email of user
 * password String Password of user
 * no response value expected for this operation
 **/
exports.usersPOST = function(email,password) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * Update user
 * Update a user.
 *
 * email String Email of user
 * password String Password of user
 * no response value expected for this operation
 **/
exports.usersPUT = function(email,password) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * get all products of user
 * Get all product of user
 *
 * returns ProductUser
 **/
exports.usersProductsGET = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "gtin" : {
    "compagny" : {
      "name" : "name"
    },
    "name" : "name",
    "id" : 0
  },
  "purchaseLocation" : {
    "address" : "address",
    "city" : "city",
    "name" : "name",
    "online" : true,
    "id" : 1
  },
  "documents" : {
    "fileName" : "filename.jpg",
    "id" : 6
  },
  "price" : "price",
  "name" : "name",
  "datePurchase" : "datePurchase",
  "id" : 0,
  "user" : {
    "username" : "username"
  },
  "dateEndConstructorWarranty" : "dateEndConstructorWarranty",
  "dateEndCommercialWarranty" : "dateEndCommercialWarranty"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * delete a product of user by id
 * delete a product of user by id
 *
 * id String 
 * no response value expected for this operation
 **/
exports.usersProductsIdDELETE = function(id) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * get a product of user by id
 * Get a product of user by id
 *
 * id String 
 * returns ProductUser
 **/
exports.usersProductsIdGET = function(id) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "gtin" : {
    "compagny" : {
      "name" : "name"
    },
    "name" : "name",
    "id" : 0
  },
  "purchaseLocation" : {
    "address" : "address",
    "city" : "city",
    "name" : "name",
    "online" : true,
    "id" : 1
  },
  "documents" : {
    "fileName" : "filename.jpg",
    "id" : 6
  },
  "price" : "price",
  "name" : "name",
  "datePurchase" : "datePurchase",
  "id" : 0,
  "user" : {
    "username" : "username"
  },
  "dateEndConstructorWarranty" : "dateEndConstructorWarranty",
  "dateEndCommercialWarranty" : "dateEndCommercialWarranty"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * update a product to a user
 * Update a product to a user
 *
 * id String 
 * formPutProductUser FormPutProductUser_1  (optional)
 * returns ProductUser
 **/
exports.usersProductsIdPUT = function(id,formPutProductUser) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "gtin" : {
    "compagny" : {
      "name" : "name"
    },
    "name" : "name",
    "id" : 0
  },
  "purchaseLocation" : {
    "address" : "address",
    "city" : "city",
    "name" : "name",
    "online" : true,
    "id" : 1
  },
  "documents" : {
    "fileName" : "filename.jpg",
    "id" : 6
  },
  "price" : "price",
  "name" : "name",
  "datePurchase" : "datePurchase",
  "id" : 0,
  "user" : {
    "username" : "username"
  },
  "dateEndConstructorWarranty" : "dateEndConstructorWarranty",
  "dateEndCommercialWarranty" : "dateEndCommercialWarranty"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * add a product to a user
 * Add a product to a user
 *
 * formPutProductUser FormPutProductUser  (optional)
 * returns ProductUser
 **/
exports.usersProductsPOST = function(formPutProductUser) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "gtin" : {
    "compagny" : {
      "name" : "name"
    },
    "name" : "name",
    "id" : 0
  },
  "purchaseLocation" : {
    "address" : "address",
    "city" : "city",
    "name" : "name",
    "online" : true,
    "id" : 1
  },
  "documents" : {
    "fileName" : "filename.jpg",
    "id" : 6
  },
  "price" : "price",
  "name" : "name",
  "datePurchase" : "datePurchase",
  "id" : 0,
  "user" : {
    "username" : "username"
  },
  "dateEndConstructorWarranty" : "dateEndConstructorWarranty",
  "dateEndCommercialWarranty" : "dateEndCommercialWarranty"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

