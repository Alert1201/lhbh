<script type="text/javascript" class="init">
$(function() {

    // init the tree
    $('#tree').aciTree({
        ajax: {
    //    url: "../resources/json/sample2.json"
        url: "/lhbh/categories/getTree"
        },
        selectable: true
    });

});

</script>

