<script t                 +ype="text/javascript" class="init">
$(function() {
	   // listen for the events
    $('#tree').on('acitree', function(event, api, item, eventName, options) {
        if (eventName == 'selected'){
            // do something when a item is selected
            var itemData = api.itemData(item);
            alert('You just selected the item with the ID: ' + api.getId(item) + '\n' +
                'also the custom property [level] equals: ' + itemData['level']);
        }
        
    });
    // init the tree
    $('#tree').aciTree({
        ajax: {
    //    url: "../resources/json/sample2.json"
        url: "/lhbh/categories/getTree"
        },
        selectable: true
    }).on('acitree', function(event, api, item, eventName, options) {
 
        // tell what tree it is
        var index = ($(this).attr('id') == 'tree') ? 0 : 1;
 
        switch (eventName) {
 
            case 'init':
 
                // set focus so we can use the keyboard already
                $('#tree').focus();
 
                break;
 
        }
 
    });
    
});

</script>

