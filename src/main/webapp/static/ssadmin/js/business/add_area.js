$(document).ready(function () {
    $("input[name='roleCbx']").click(function () {
        var arr = [];
        $("input[name='roleCbx']:checked").each(function(i){
            arr[i] = $(this).val();
        })
        var roleIds = arr.join(",");
        console.log("当前所有选中的值： " + roleIds);
        $("input[name='roleIds']").val(roleIds);
    });
});