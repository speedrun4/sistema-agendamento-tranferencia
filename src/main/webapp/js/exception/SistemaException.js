sgt.exception.SistemaException = function (message) {
  this.name = "NegocioException";
  this.message = (message || "");
}

sgt.exception.SistemaException.prototype = new Error();
sgt.exception.SistemaException.constructor = sgt.exception.SistemaException;