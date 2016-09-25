<script t                 +ype="text/javascript" class="init">
$(function() {
	   // listen for the events
    $('#tree').on('acitree', function(event, api, item, eventName, options) {
        if (eventName == 'selected'){
            // do something when a item is selected
            var itemData = api.itemData(item);
            $('#itemLabel').text("Label = " + itemData['label']);
            $('#itemId').text("Id = " + itemData['id']);
            $('#itemOrder').text("Order = " + itemData['order']); 
            var parent = api.parent(item);
            if(api.hasChildren(parent)){
            	confirm("Current " + itemData['order'] + " Item = " + itemData['label'])
                //	$('#itemDisp').text("First Label = " + firstItemData['label']);
            	while(api.hasNext(item)){	
            		item = api.next(item)
            		itemData = api.itemData(item);
            		confirm("Current " + itemData['order'] + " Item = " + itemData['label'])
            	}
            }
            
      //     alert('You just selected the item with the ID: ' + api.getId(item) + '\n' +
        //        'also the custom property [level] equals: ' + itemData['level']);
        }
        
    });
    // init the tree
    $('#tree').aciTree({
        ajax: {
        url: "/lhbh/categories/getTree"
        },
        selectable: true
    })    
    
    $('#tree').contextMenu({
        selector: '.aciTreeLine',
        build: function(element) {
            var api = $('#tree').aciTree('api');
            var item = api.itemFrom(element);
            var itemData = api.itemData(item);
            var menu = {
            };
            if (api.isInode(item)) {
                    if (api.isOpen(item)) {
                        menu['toggle'] = {
                            name: 'Toggle',icon:'fa-toggle-off',
                            callback: function() {
                                api.toggle(item);
                            }
                        };
                    } else {
                        menu['toggle'] = {
                            name: 'Toggle',icon:'fa-toggle-on',
                            callback: function() {
                                api.toggle(item);
                            }
                        };
                    }
            } else {
                menu['remove'] = {
                    name: 'Remove item',icon:'fa-trash-o',
                    callback: function() {
                        var next = api.next(item);
                        var prev = api.prev(item);
                        var parent = api.parent(item);
                        if(!confirm("Delete Item. Are you sure? " + "Id = " + itemData['id']+" Parent ID = "+itemData['parentId']+" Order = "+itemData['order'])){
                        	return;
                        }
                        //, parId: itemData['parentId'], order:itemData['order']
                		$.ajax({
                			url : "removeItem",
                			data: {
                				id: itemData['id'], parId: itemData['parentId'], levelOrder: itemData['order']},
                			success : function(data) {
                				api.remove(item);
                                if (next.length) {
                                    // select the next tree item
                                    api.select(next);
                                } else if (prev.length) {
                                    // select the previous tree item
                                    api.select(prev);
                                    confirm("we are at the end. Return");
                                    //Return. We are done. Last Child we do not need to redorder.
                                    return;
                                } else {
                                	api.select(parent);
                                }
                                confirm("Got here");
                                //Runumber items
                                if(api.hasChildren(parent)){
                                	while(api.hasNext(item)){	
                                		item = api.next(item)
                                		itemData = api.itemData(item);
                                		confirm("Current " + itemData['order'] + " Item = " + itemData['label'])
                                	}
                                }
                			},
                			error : function(jqXHR, textStatus, errorThrown) {
                				alert(jqXHR.status);
                				alert(textStatus);
                			}
                		});
                    }
                };
            }
            return {
                autoHide: true,
                items: menu
            };
        }
    });
    
});

</script>

