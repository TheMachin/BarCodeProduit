'use strict';


/**
 * get all document from an user product
 * Get all document from an user product
 *
 * id String 
 * returns Document
 **/
exports.usersProductsIdDocumentsGET = function(id) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "fileName" : "filename.jpg",
  "id" : 6
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update a new document
 * Update a document with a file and a filename
 *
 * id String 
 * idDoc String 
 * no response value expected for this operation
 **/
exports.usersProductsIdDocumentsIdDocDELETE = function(id,idDoc) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * get a document from an user product
 * a all document from an user product
 *
 * id String 
 * idDoc String 
 * returns Document
 **/
exports.usersProductsIdDocumentsIdDocGET = function(id,idDoc) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "fileName" : "filename.jpg",
  "id" : 6
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update a new document
 * Update a document with a file and a filename
 *
 * id String 
 * idDoc String 
 * file File the uploaded file data
 * fileName String the name of file data
 * returns Document
 **/
exports.usersProductsIdDocumentsIdDocPUT = function(id,idDoc,file,fileName) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "fileName" : "filename.jpg",
  "id" : 6
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * create a new document
 * Create a new document with a file and a filename
 *
 * id String 
 * file File the uploaded file data
 * fileName String the name of file data
 * returns Document
 **/
exports.usersProductsIdDocumentsPOST = function(id,file,fileName) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "fileName" : "filename.jpg",
  "id" : 6
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * download a document from an user product
 * download document from an user product
 *
 * id String 
 * idDoc String 
 * no response value expected for this operation
 **/
exports.usersProductsIdDocumentsidDocDownloadGET = function(id,idDoc) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

