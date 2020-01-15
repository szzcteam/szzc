//2个数字求和后，保留2位整数，超出2位小数的四舍五入
function funSumByRound(num1, num2) {
    if (isNaN(num1) || isNaN(num2)) {
        return 0;
    }
    var value = Math.round(eval(num1 + "*" + num2) * 100) / 100;
    return value;
}
