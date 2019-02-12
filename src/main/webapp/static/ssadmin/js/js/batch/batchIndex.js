function marktunit(buys, sells) {
    var tmpBuy = buys.concat(sells);
    if (tmpBuy.length <= 0) {
        return;
    }
    tmpBuy.sort(function (a, b) {
        return a.amount - b.amount;
    });
    var i = Math.floor((tmpBuy.length / 3) * 2, 0);
    return tmpBuy[i].amount < 1 ? 1 : tmpBuy[i].amount;
};
/**
 * 自动刷新
 */
function autoRdefresh(){
    var symbol = $("#symbol").val();
    var coinCount1 = $("#coinCount1").val();
    var coinCount2 = $("#coinCount2").val();
    var url = "/ssadmin/batchMarket.html?symbol=" + symbol;
    url = url + "&random=" + Math.round(Math.random() * 100);
    $("#progressBar").remove();
    $("#background").remove();
    $.get(url, function (data) {
        if (data != "") {
            $("#buybox").html("");
            $.each(data.buys, function (key, value) {
                var html = "<div class='buy-item'>";
                html += "<span>" + util.moneyformat(Number(value.amount), coinCount2) + "</span>";
                html += "<span>" + util.moneyformat(Number(value.price), coinCount1) + "</span>";
                html += "<span>买"+ (key + 1) +"</span></div>";
                $("#buybox").append(html);
            });
            $("#sellbox").html("");
            $.each(data.sells, function (key, value) {
                var html = "<div class='sell-item'>";
                html += "<span>卖"+ (key + 1) +"</span>";
                html += "<span>" + util.moneyformat(Number(value.price), coinCount1) + "</span>";
                html += "<span>" + util.moneyformat(Number(value.amount), coinCount2) + "</span></div>";
                $("#sellbox").append(html);
            });
        };
    }, "json");
    loadWallet();
    loadEntrustInfo();
    /*window.setTimeout(function () {
        autoRdefresh();
    }, 5000);*/
}

function loadEntrustInfo(){
    var symbol = $("#symbol").val();
    var orderType = $("#orderType").val();
    var url = "/ssadmin/entrustInfo.html?symbol=" + symbol + "&orderType="+ orderType +"&type=0&tradeType=0&random=" + Math.round(Math.random() * 100);
    $.post(url,null,function(data){
        /*if(data == null || data.length == 0) {
            window.location.href = '/' ;
            return;
        }*/
        $("#entrustInfo").html(data);
    });
}

function loadWallet(){
    var symbol = $("#symbol").val();
    var coinCount1 = $("#coinCount1").val();
    var coinCount2 = $("#coinCount2").val();
    var url = "/ssadmin/batchAccountWallet.html?symbol=" + symbol;
    url = url + "&random=" + Math.round(Math.random() * 100);
    $.get(url, function (data) {
        if (data != "") {
            $.each(data.wallet, function (key, value) {
                var index = key + 1;
                $("#userWallet" + index).html("<span class='"+ value.type +"'>" + value.wallet + "</span>")
            });
        }
    });
}