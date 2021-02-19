//阿拉伯数字转换为中文汉字 900 -> 玖佰
function Araia_To_Chinese(num) {
    var amount = numberFormatOptions.format(num, true, true);
    return amount;
}

//定义数字格式化对象
var numberFormatOptions = new Object();

//事先定义好固定数组
numberFormatOptions.simple_digits = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");
numberFormatOptions.traditional_digits = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖");
numberFormatOptions.simple_units = new Array("", "十", "百", "千");
numberFormatOptions.traditional_units = new Array("", "拾", "佰", "仟");

/**
 * 阿拉伯数字转换成中文
 * 小数点后四舍五入保留2位小数
 * @param amount  阿拉伯数字
 * @param isUseTraditional   是否使用金额大写
 * @param isMoneyMode  是否是金额模式
 */
numberFormatOptions.format = function (amount, isUseTraditional, isMoneyMode) {
    var numArray = null;
    var units = null;
    if (isUseTraditional) {
        numArray = numberFormatOptions.traditional_digits;
    } else {
        numArray = numberFormatOptions.simple_digits;
    }

    if (amount < -99999999999999.99 || amount > 99999999999999.99) {
        console.log("不支持的数字 " + amount);
        return amount;
    }

    //是负数
    var negative = false;
    if (amount < 0.0) {
        negative = true;
        amount = -amount;
    }

    var temp = Math.round(amount * 100);
    //分
    var numFen = Math.ceil(temp % 10)
    temp /= 10;
    temp = parseInt(temp);
    //角
    var numJiao = Math.ceil(temp % 10);
    temp /= 10;
    temp = parseInt(temp);
    var parts = new Array(20);
    var numParts = 0;

    for (var i = 0; temp != 0; ++i) {
        var part = Math.ceil(temp % 10000)
        parts[i] = part;
        ++numParts;
        temp /= 10000;
        temp = parseInt(temp);
    }

    var beforeWanIsZero = true;
    var chineseStr = "";
    for (var i = 0; i < numParts; ++i) {
        var partChinese = numberFormatOptions.toChinese(parts[i], isUseTraditional);
        if (i % 2 == 0) {
            if (partChinese == "") {
                beforeWanIsZero = true;
            } else {
                beforeWanIsZero = false;
            }
        }

        if (i != 0) {
            if (i % 2 == 0) {
                chineseStr = "亿" + chineseStr;
            } else if (partChinese == "" && !beforeWanIsZero) {
                chineseStr = "零" + chineseStr;
            } else {
                if (parts[i - 1] < 1000 && parts[i - 1] > 0) {
                    chineseStr = "零" + chineseStr;
                }

                if (parts[i] > 0) {
                    chineseStr = "万" + chineseStr;
                }
            }
        }

        chineseStr = partChinese + chineseStr;
    }

    //为空，就直接是零
    if ("" == chineseStr) {
        chineseStr = numArray[0];
    }

    if (negative) {
        chineseStr = "负" + chineseStr;
    }

    if (numFen == 0 && numJiao == 0) {
        if (isMoneyMode) {
            chineseStr = chineseStr + "元整";
        }
    } else if (numFen == 0) {
        if (isMoneyMode) {
            chineseStr = chineseStr + "元";
        } else {
            chineseStr = chineseStr + "点";
        }

        chineseStr = chineseStr + numArray[numJiao];
        if (isMoneyMode) {
            chineseStr = chineseStr + "角";
        } else {
            chineseStr = chineseStr + "";
        }
    } else if (numJiao == 0) {
        if (isMoneyMode) {
            chineseStr = chineseStr + "元零";
        } else {
            chineseStr = chineseStr + "点零";
        }
        chineseStr = chineseStr + numArray[numFen];
        if (isMoneyMode) {
            chineseStr = chineseStr + "分";
        } else {
            chineseStr = chineseStr + "";
        }
    } else {
        if (isMoneyMode) {
            chineseStr = chineseStr + "元";
        } else {
            chineseStr = chineseStr + "点";
        }
        chineseStr = chineseStr + numArray[numJiao];
        if (isMoneyMode) {
            chineseStr = chineseStr + "角";
        } else {
            chineseStr = chineseStr + "";
        }
        chineseStr = chineseStr + numArray[numFen];
        if (isMoneyMode) {
            chineseStr = chineseStr + "分";
        } else {
            chineseStr = chineseStr + "";
        }
    }

    return chineseStr.toString();
}


//转中文
numberFormatOptions.toChinese = function (amountPart, isUseTraditional) {
    var numArray = null;
    var units = null;
    if (isUseTraditional) {
        numArray = numberFormatOptions.traditional_digits;
        units = numberFormatOptions.traditional_units;
    } else {
        numArray = numberFormatOptions.simple_digits;
        units = numberFormatOptions.simple_units;
    }

    var temp = amountPart;
    var chineseStr = "";
    var lastIsZero = true;

    for (var i = 0; temp > 0; ++i) {
        var digit = temp % 10;
        if (digit == 0) {
            if (!lastIsZero) {
                chineseStr = "零" + chineseStr;  //第0位 设为 零
            }
            lastIsZero = true;
        } else {
            lastIsZero = false;
            chineseStr = numArray[digit] + units[i] + chineseStr;  //第0位设为 具体数字
        }
        temp /= 10;
        temp = parseInt(temp);
    }

    return chineseStr.toString();

}