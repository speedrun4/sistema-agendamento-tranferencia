    ko.bindingHandlers.mask = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
            $(element).mask(ko.utils.unwrapObservable(valueAccessor())).bind('blur', function (e) {
                allBindingsAccessor().value($(element).val());
            });
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel) {                                            

            $(element).mask(ko.utils.unwrapObservable(valueAccessor()), { completed: function() {                
                // execute callBack function                    
            }});
        }
    };